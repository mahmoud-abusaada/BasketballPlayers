package com.abusada.basketballplayers.service.models;

import com.google.gson.annotations.SerializedName;

public class Meta {
    @SerializedName("current_page")
    private Integer currentPage;
    @SerializedName("next_page")
    private Integer nextPage;
    @SerializedName("per_page")
    private Integer perPage;
    @SerializedName("total_count")
    private Integer totalCount;
    @SerializedName("total_pages")
    private Integer totalPages;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
