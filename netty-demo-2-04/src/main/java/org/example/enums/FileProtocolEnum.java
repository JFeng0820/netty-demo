package org.example.enums;

public enum FileProtocolEnum {
    FILE_DESC_INFO(0, "请求传输文件", "FileDescInfo"),
    FILE_BURST_INSTRUCT(1, "文件传输指令", "FileBurstInstruct"),
    FILE_BURST_DATA(2, "文件传输数据", "FileBurstData"),
    FILE_UNKNOWN(99, "未知类型", "FileUnknown"),
    ;

    private final int code;
    private final String label;
    private final String clazzName;

    FileProtocolEnum(int code, String label, String clazzName) {
        this.code = code;
        this.label = label;
        this.clazzName = clazzName;
    }

    public static FileProtocolEnum getProtocol(Integer code) {
        switch(code){
            case 0:
                return FileProtocolEnum.FILE_DESC_INFO;
            case 1:
                return FileProtocolEnum.FILE_BURST_INSTRUCT;
            case 2:
                return FileProtocolEnum.FILE_BURST_DATA;
            default:
                return FileProtocolEnum.FILE_UNKNOWN;
        }
    }

    public int getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public String getClazzName() {
        return clazzName;
    }
}
