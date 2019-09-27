package kz.saa.prototype.models.pojos.impl;

import kz.saa.prototype.models.pojos.ExternalUser;
import lombok.Builder;

@Builder
public class DefaultExternalUser implements ExternalUser {
    private Long exId;
    private String iin;
    private String placeOfWork;
    private Long userId;
    private String firstName;
    private String lastName;
    private String username;

    @Override
    public Long getExId() {
        return exId;
    }

    @Override
    public String getIin() {
        return iin;
    }

    @Override
    public String getPlaceOfWork() {
        return placeOfWork;
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
