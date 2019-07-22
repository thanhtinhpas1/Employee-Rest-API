package test.demo.model;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class BaseResponse {
    private int status;
    private String message;
    private Object data;

    public BaseResponse() {
       setStatus(HttpStatus.OK);
       setMessage(HttpStatus.OK);
       setData(null);
    }

    public void setStatus(HttpStatus mStatus) {
        switch (mStatus) {
        case OK:
            status = 200;
            break;
        case CREATED:
            status = 201;
            break;
        case NO_CONTENT:
            status = 204;
            break;
        case BAD_REQUEST:
            status = 400;
            break;
        case UNAUTHORIZED:
            status = 401;
            break;
        case FORBIDDEN:
            status = 403;
            break;
        case NOT_FOUND:
            status = 404;
            break;
        case CONFLICT:
            status = 404;
            break;
        case INTERNAL_SERVER_ERROR:
            status = 500;
            break;
        }
        setMessage(mStatus);
    }

    public void setMessage(HttpStatus statusEnum) {
        switch(statusEnum) {
            case OK:
            message = "Thành công";
            break;
            case CREATED:
            message = "Đã tạo thành công";
            break;
            case NO_CONTENT:
            message = "Không có dữ liệu trả về";
            break;
            case BAD_REQUEST:
            message = "Yêu cầu không hợp lệ";
            break;
            case UNAUTHORIZED:
            message = "Không có quyền truy cập";
            break;
            case FORBIDDEN:
            message = "Truy cập không được phép";
            break;
            case NOT_FOUND:
            message = "Không tìm thấy";
            break;
            case CONFLICT:
            message = "Xảy ra đụng độ";
            break;
            case INTERNAL_SERVER_ERROR:
            message = "Có lỗi ở Server";
            break;
        }
    }

    public void setMessage(String msg) {
        this.message = msg;
    }
}