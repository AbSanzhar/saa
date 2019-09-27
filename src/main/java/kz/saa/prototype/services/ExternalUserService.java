package kz.saa.prototype.services;

import kz.saa.prototype.models.pojos.impl.DefaultExternalUser;
import kz.saa.prototype.models.pojos.json.ExternalUserJson;

public interface ExternalUserService {

    DefaultExternalUser showExternalUser(Long userId) throws Exception;

    String addExternalUser(ExternalUserJson externalUserJson);
}
