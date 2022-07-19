package co.kr.eatthis.common.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class PageNavigator {
    /**
     * 한 페이지당 게시글 수
     **/
    private int pageSize = 10;

    /**
     * 한 블럭 사이즈
     **/
    private int blockSize = 10;

    /**
     * 현재 페이지
     **/
    private int page = 1;

    /**
     * 현재 블럭(range)
     **/
    private int currentBlock = 1;

    /**
     * 총 게시글 수
     **/
    private int totalCount;

    /**
     * 총 페이지 수
     **/
    private int totalPageCount;

    /**
     * 총 블럭 수
     **/
    private int totalBlockCount;

    /**
     * 시작 페이지
     **/
    private int startPage = 1;

    /**
     * 끝 페이지
     **/
    private int endPage;

    /**
     * 시작 index
     **/
    private int startIndex = 0;

    /**
     * 이전 페이지
     **/
    private int previousPage;

    /**
     * 다음 페이지
     **/
    private int nextPage;

    public PageNavigator() {
    }

    /**
     * 페이징 처리
     **/
    public void setTotalCount(int totalCount, int page, int pageSize){
        /** 총 게시물 수 **/
        this.totalCount = totalCount;
        this.page = page;
        this.pageSize = pageSize;

        /** 총 페이지 수 **/
        setTotalPageCount(totalCount);

        /** 총 블럭 수 **/
        setBlockCount(totalPageCount);

        /** 블럭 설정 **/
        blockSetting(this.page);

        /** DB 질의를 위한 startIndex 설정 **/
        setStartIndex(this.page);
    }

    public void setTotalCount(int totalCount) {
        setTotalCount(totalCount, 1, 10);
    }

    private void setTotalPageCount(int listCount) {
        this.totalPageCount = (int) Math.ceil(listCount * 1.0 / pageSize);
    }

    private void setBlockCount(int pageCount) {
        this.totalBlockCount = (int) Math.ceil(pageCount * 1.0 / blockSize);
    }

    private void blockSetting(int currentPage) {
        setCurrentBlock(currentPage);
        this.startPage = (currentBlock - 1) * blockSize + 1;
        this.endPage = startPage + blockSize - 1;
        if (endPage > totalPageCount) {
            this.endPage = totalPageCount;
        }
        this.previousPage = currentPage - 1;
        this.nextPage = currentPage + 1;
    }

    private void setCurrentBlock(int currentPage) {
        this.currentBlock = (int) ((currentPage - 1) / blockSize) + 1;
    }

    private void setStartIndex(int currentPage) {
        this.startIndex = (currentPage - 1) * pageSize;
    }

    @Override
    public String toString() {
        return "PageNavigator{" +
                "pageSize=" + pageSize +
                ", blockSize=" + blockSize +
                ", page=" + page +
                ", currentBlock=" + currentBlock +
                ", totalCount=" + totalCount +
                ", totalPageCount=" + totalPageCount +
                ", totalBlockCount=" + totalBlockCount +
                ", startPage=" + startPage +
                ", endPage=" + endPage +
                ", startIndex=" + startIndex +
                ", previousPage=" + previousPage +
                ", nextPage=" + nextPage +
                '}';
    }
}
