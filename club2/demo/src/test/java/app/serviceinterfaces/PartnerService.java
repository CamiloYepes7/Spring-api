
package app.serviceinterfaces;


import app.dto.UserDto;

public interface PartnerService {
    public void createGuest (UserDto userDto) throws Exception;
    public void desactivateGuest(long document) throws Exception;
}
