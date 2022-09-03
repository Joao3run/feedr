package br.com.brn.feedrapi.application.domain.filters;

import javax.validation.constraints.NotNull;
import java.time.Instant;

public abstract class Filter {

    private Long id;

    private Instant startDate;

    private Instant endDate;

    @NotNull
    private Boolean onlyActives;

    public Long getId() {
        return id != null ? id : 0;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Boolean getOnlyActives() {
        return onlyActives;
    }

    public void setOnlyActives(Boolean onlyActives) {
        this.onlyActives = onlyActives;
    }
}
