package com.conoz.singletour.bean;

import java.io.Serializable;

/**
 * Created by Yongkyu on 2017. 5. 5..
 */

public class ResultResponse implements Serializable {
    private static final long serialVersionUID = -9015642915416660664L;
    private ResultInfo result;
    private StatusInfo status;


    public ResultInfo getResult() {
        return result;
    }
    public StatusInfo getStatus() {
        return status;
    }

    public class StatusInfo {
        private String statusCd;
        private String statusMssage;

        public String getStatusCd() {
            return statusCd;
        }

        public String getStatusMssage() {
            return statusMssage;
        }
    }
}
