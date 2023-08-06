package com.dcm.spring.druginfo.model;

import lombok.Data;
import java.util.List;

@Data
public class FoodResponseDto {
    private Header header;
    private Body body;

    @Data
    public static class Header {
        private String resultCode;
        private String resultMessage; // JSON에서는 resultMessage로 명시되어 있습니다.
    }

    @Data
    public static class Body {
        private List<ItemWrapper> items; // List<Object> 대신에 아래의 ItemWrapper를 사용
        private String totalCount;
        private String pageNo;
        private String numOfRows;
    }

    @Data
    public static class ItemWrapper {
        private Item item;
    }

    @Data
    public static class Item {
        private String prdkindstate;
        private String nutrient;
        private String manufacture;
        private String rnum;
        private String prdkind;
        private String rawmtrl;
        private String prdlstNm;
        private String imgurl2;
        private String barcode;
        private String imgurl1;
        private String productGb;
        private String prdlstReportNo;
        private String allergy;
    }
}

