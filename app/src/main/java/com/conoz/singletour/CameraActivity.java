package com.conoz.singletour;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class CameraActivity extends Activity implements View.OnTouchListener, SurfaceHolder.Callback{
    private static final String TAG     =   "CameraActivity";
    private static final int REQUEST_CAMERA = 1;
    private static final int REQUEST_EXTERNAL_STORAGE = 2;

    private Matrix matrix = new Matrix();
    private Matrix savedMatrix = new Matrix();
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;
    private PointF start = new PointF();
    private PointF mid = new PointF();
    private float oldDist = 1f;
    private float d = 0f;
    private float newRot = 0f;
    private float[] lastEvent = null;
    String logoImageId = "";
    Bitmap bitmap = null;
    private Camera mCamera = null;
    private SurfaceView cameraSurfaceView = null;
    private SurfaceHolder cameraSurfaceHolder = null;
    private boolean previewing = false;
    RelativeLayout relativeLayout;
    int currentCameraId = 0;
    private Button btnCapture = null;
    ImageButton useOtherCamera = null;
    ImageView logoImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_camera);

        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_EXTERNAL_STORAGE);
            }
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED){ //권한 없음
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
            }else{      // 권한 있음
                init();
            }
        }else{
            init();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CAMERA:
                for (int i = 0; i < permissions.length; i++) {
                    String permission = permissions[i];
                    int grantResult = grantResults[i];
                    if (permission.equals(Manifest.permission.CAMERA)) {
                        if (grantResult == PackageManager.PERMISSION_GRANTED) {
                            init();
                        } else {
                            finish();
                        }
                    }
                }
                break;
            case REQUEST_EXTERNAL_STORAGE:
                for (int i = 0; i < permissions.length; i++) {
                    String permission = permissions[i];
                    int grantResult = grantResults[i];
                    if (permission.equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        if (grantResult == PackageManager.PERMISSION_GRANTED) {
                        } else {
                        }
                    }
                }
                break;
        }
    }

    private void init(){
        logoImageView = (ImageView) findViewById(R.id.logoImageView);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            logoImageId = extras.getString("logoImageId");
        }
        try {
            /*File file = new File(Environment.getExternalStorageDirectory()
                    + "/" + getPackageName() + "/logo/" + logoImageId
                    + ".jpg");
            bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());*/
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.friend);  //함께 찍을 사진.
            logoImageView.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        logoImageView.setOnTouchListener(this);
        relativeLayout = (RelativeLayout) findViewById(R.id.containerImg);
        relativeLayout.setDrawingCacheEnabled(true);

//        cameraSurfaceView = (SurfaceView) findViewById(R.id.surfaceView);
//        cameraSurfaceHolder = cameraSurfaceView.getHolder();
//        cameraSurfaceHolder.addCallback(this);
//
        btnCapture = (Button) findViewById(R.id.button);
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        cameraSurfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        cameraSurfaceHolder = cameraSurfaceView.getHolder();
        cameraSurfaceHolder.addCallback(this);

    }

    private void takePicture(){
        if(mCamera !=null){
            mCamera.takePicture(null, null, cameraPictureCallbackJpeg);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            mCamera = Camera.open();
            Camera.Parameters params = mCamera.getParameters();

            // Check what resolutions are supported by your mCamera
            List<Camera.Size> sizes = params.getSupportedPictureSizes();

            // setting small image size in order to avoid OOM error
            Camera.Size cameraSize = null;
            for (Camera.Size size : sizes) {
                //set whatever size you need
                //if(size.height<500) {
                cameraSize = size;
                break;
                //}
            }

            if (cameraSize != null) {
                params.setPictureSize(cameraSize.width, cameraSize.height);
                mCamera.setParameters(params);

                float ratio = relativeLayout.getHeight()*1f/cameraSize.height;
                float w = cameraSize.width*ratio;
                float h = cameraSize.height*ratio;
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams((int)w, (int)h);
                cameraSurfaceView.setLayoutParams(lp);
            }
        } catch (RuntimeException e) {
            Toast.makeText(this, "카메라가 정상 동작하지 않습니다. 잠시후 다시 시도해 보세요.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (previewing) {
            mCamera.stopPreview();
            previewing = false;
        }
        try {

            if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
                mCamera.setDisplayOrientation(90);
                Camera.Size cameraSize = mCamera.getParameters().getPictureSize();
                int wr = relativeLayout.getWidth();
                int hr = relativeLayout.getHeight();
                float ratio = relativeLayout.getWidth()*1f/cameraSize.height;
                float w = cameraSize.width*ratio;
                float h = cameraSize.height*ratio;
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams((int)h, (int)w);
                cameraSurfaceView.setLayoutParams(lp);
            }else {
                mCamera.setDisplayOrientation(0);
                Camera.Size cameraSize = mCamera.getParameters().getPictureSize();
                float ratio = relativeLayout.getHeight()*1f/cameraSize.height;
                float w = cameraSize.width*ratio;
                float h = cameraSize.height*ratio;
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams((int)w, (int)h);
                cameraSurfaceView.setLayoutParams(lp);
            }

            mCamera.setPreviewDisplay(cameraSurfaceHolder);
            mCamera.startPreview();
            previewing = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if(mCamera !=null){
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
        previewing = false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ImageView view = (ImageView) v;
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                savedMatrix.set(matrix);
                start.set(event.getX(), event.getY());
                mode = DRAG;
                lastEvent = null;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                oldDist = spacing(event);
                if (oldDist > 10f) {
                    savedMatrix.set(matrix);
                    midPoint(mid, event);
                    mode = ZOOM;
                }
                lastEvent = new float[4];
                lastEvent[0] = event.getX(0);
                lastEvent[1] = event.getX(1);
                lastEvent[2] = event.getY(0);
                lastEvent[3] = event.getY(1);
                d = rotation(event);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                mode = NONE;
                lastEvent = null;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mode == DRAG) {
                    matrix.set(savedMatrix);
                    float dx = event.getX() - start.x;
                    float dy = event.getY() - start.y;
                    matrix.postTranslate(dx, dy);
                } else if (mode == ZOOM) {
                    float newDist = spacing(event);
                    if (newDist > 10f) {
                        matrix.set(savedMatrix);
                        float scale = (newDist / oldDist);
                        matrix.postScale(scale, scale, mid.x, mid.y);
                    }
                    if (lastEvent != null && event.getPointerCount() == 3) {
                        newRot = rotation(event);
                        float r = newRot - d;
                        float[] values = new float[9];
                        matrix.getValues(values);
                        float tx = values[2];
                        float ty = values[5];
                        float sx = values[0];
                        float xc = (view.getWidth() / 2) * sx;
                        float yc = (view.getHeight() / 2) * sx;
                        matrix.postRotate(r, tx + xc, ty + yc);
                    }
                }
                break;
        }

        view.setImageMatrix(matrix);

        return true;
    }

    /**
     * 처음 두 손가락 사이의 공간을 결정.
     * @param event
     * @return
     */
    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float)Math.sqrt(x * x + y * y);
    }

    /**
     * 처음 두손가락 사이의 중앙 결정.
     * @param point
     * @param event
     */
    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    /**
     * 회전될 비율 결정.
     * @param event
     * @return
     */
    private float rotation(MotionEvent event) {
        double delta_x = (event.getX(0) - event.getX(1));
        double delta_y = (event.getY(0) - event.getY(1));
        double radians = Math.atan2(delta_y, delta_x);
        return (float) Math.toDegrees(radians);
    }

    Camera.PictureCallback cameraPictureCallbackJpeg = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            //o.inJustDecodeBounds = true;
            Bitmap cameraBitmapNull = BitmapFactory.decodeByteArray(data, 0, data.length, options);
            int wid = options.outWidth;
            int hgt = options.outHeight;
            Matrix nm = new Matrix();
            Camera.Size cameraSize = camera.getParameters().getPictureSize();
            float ratio = relativeLayout.getHeight()*1f/cameraSize.height;
            if (getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
                nm.postRotate(90);
                nm.postTranslate(hgt, 0);
                wid = options.outHeight;
                hgt = options.outWidth;
                ratio = relativeLayout.getWidth()*1f/cameraSize.height;

            }else {
                wid = options.outWidth;
                hgt = options.outHeight;
                ratio = relativeLayout.getHeight()*1f/cameraSize.height;
            }

            float[] f = new float[9];
            matrix.getValues(f);

            f[0] = f[0]/ratio;
            f[4] = f[4]/ratio;
            f[5] = f[5]/ratio;
            f[2] = f[2]/ratio;
            matrix.setValues(f);

            Bitmap newBitmap = Bitmap.createBitmap(wid, hgt,
                    Bitmap.Config.ARGB_8888);

            Canvas canvas = new Canvas(newBitmap);
            Bitmap cameraBitmap = BitmapFactory.decodeByteArray(data, 0,
                    data.length, options);

            canvas.drawBitmap(cameraBitmap, nm, null);
            cameraBitmap.recycle();

            canvas.drawBitmap(bitmap, matrix, null);
            bitmap.recycle();

            insertImage(getContentResolver(), newBitmap,Long.toString(System.currentTimeMillis()),"");
            Toast.makeText(getApplicationContext(), "사진이 갤러리에 저장되었습니다.", Toast.LENGTH_LONG).show();
            finish();
            /*
            File storagePath = new File(Environment.getExternalStorageDirectory() + "/PhotoAR/");
            storagePath.mkdirs();

            File myImage = new File(storagePath, Long.toString(System.currentTimeMillis()) + ".jpg");
            try {
                FileOutputStream out = new FileOutputStream(myImage);
                newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                Log.d("In Saving File", e + "");
            } catch (IOException e) {
                Log.d("In Saving File", e + "");
            }
            */
        }
    };

    public static final String insertImage(ContentResolver cr, Bitmap source, String title, String description) {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, title);
        values.put(MediaStore.Images.Media.DISPLAY_NAME, title);
        values.put(MediaStore.Images.Media.DESCRIPTION, description);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        // Add the date meta data to ensure the image is added at the front of the gallery
        values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis());
        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());

        Uri url = null;
        String stringUrl = null;    /* value to be returned */

        try {
            url = cr.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

            if (source != null) {
                OutputStream imageOut = cr.openOutputStream(url);
                try {
                    source.compress(Bitmap.CompressFormat.JPEG, 50, imageOut);
                } finally {
                    imageOut.close();
                }

                long id = ContentUris.parseId(url);
                // Wait until MINI_KIND thumbnail is generated.
                Bitmap miniThumb = MediaStore.Images.Thumbnails.getThumbnail(cr, id, MediaStore.Images.Thumbnails.MINI_KIND, null);
                // This is for backward compatibility.
                storeThumbnail(cr, miniThumb, id, 50F, 50F, MediaStore.Images.Thumbnails.MICRO_KIND);
            } else {
                cr.delete(url, null, null);
                url = null;
            }
        } catch (Exception e) {
            if (url != null) {
                cr.delete(url, null, null);
                url = null;
            }
        }

        if (url != null) {
            stringUrl = url.toString();
        }

        return stringUrl;
    }

    private static final Bitmap storeThumbnail(
            ContentResolver cr,
            Bitmap source,
            long id,
            float width,
            float height,
            int kind) {

        // create the matrix to scale it
        Matrix matrix = new Matrix();

        float scaleX = width / source.getWidth();
        float scaleY = height / source.getHeight();

        matrix.setScale(scaleX, scaleY);

        Bitmap thumb = Bitmap.createBitmap(source, 0, 0,
                source.getWidth(),
                source.getHeight(), matrix,
                true
        );

        ContentValues values = new ContentValues(4);
        values.put(MediaStore.Images.Thumbnails.KIND,kind);
        values.put(MediaStore.Images.Thumbnails.IMAGE_ID,(int)id);
        values.put(MediaStore.Images.Thumbnails.HEIGHT,thumb.getHeight());
        values.put(MediaStore.Images.Thumbnails.WIDTH,thumb.getWidth());

        Uri url = cr.insert(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, values);

        try {
            OutputStream thumbOut = cr.openOutputStream(url);
            thumb.compress(Bitmap.CompressFormat.JPEG, 100, thumbOut);
            thumbOut.close();
            return thumb;
        } catch (FileNotFoundException ex) {
            return null;
        } catch (IOException ex) {
            return null;
        }
    }
}
