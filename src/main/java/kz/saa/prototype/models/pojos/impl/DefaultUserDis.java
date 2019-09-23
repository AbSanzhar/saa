package kz.saa.prototype.models.pojos.impl;

import kz.saa.prototype.models.pojos.UserDis;
import lombok.Builder;

@Builder
public class DefaultUserDis implements UserDis {
    private Long userId;
    private String firstName;
    private String lastName;

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
}
