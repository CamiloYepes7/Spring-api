package app.service;

import app.dao.PersonDaoImplementation;
import app.dao.UserDaoImplementation;
import app.daointerfaces.PersonDao;
import app.daointerfaces.UserDao;
import app.dto.GuestDto;
import app.dto.PersonDto;
import app.dto.UserDto;
import app.serviceinterfaces.AdminService;
import app.serviceinterfaces.LoginService;
import app.serviceinterfaces.PartnerService;

public class Service implements LoginService, AdminService, PartnerService {

    private UserDao userDao;
    private PersonDao personDao;
    public static UserDto user;
    
    public Service(){
        this.userDao = new UserDaoImplementation();
        this.personDao = new PersonDaoImplementation();
    }
    
    
    public void login(UserDto userDto) throws Exception {
        UserDto validateDto = userDao.findByUserName(userDto);
        if (validateDto == null) {
            throw new Exception("no existe usuario registrado");
        }
	if (!userDto.getPassword().equals(validateDto.getPassword())) {
            throw new Exception("usuario o contraseña incorrecto");
	}
	userDto.setRole(validateDto.getRole());
	user = validateDto;
    }

    @Override
    public void logout() {
        user = null;
        System.out.println("Se ha cerrado session");
    }

    @Override
    public void createPartner(UserDto userDto) throws Exception {
        this.createUser(userDto);
    }

    @Override
    public void createGuest(UserDto userDto) throws Exception {
        this.createUser(userDto);
    }
    
    private void createUser(UserDto userDto) throws Exception{
        this.createPerson(userDto.getPersonId());
        userDto.setPersonId(personDao.findByDocument(userDto.getPersonId()));
	if(this.userDao.existsByUserName(userDto)) {
            this.personDao.deletePerson(userDto.getPersonId());
            throw new Exception("ya existe un usuario con ese user name");
            }
            this.userDao.createUser(userDto);
	}
    private void createPerson(PersonDto personDto)throws Exception{
	if(this.personDao.existsByDocument(personDto)) {
        throw new Exception("ya existe una persona con ese documento");
	}
        this.personDao.createPerson(personDto);
    }

  
    @Override
    public void promotiontovip(PersonDto personDto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void desactivateGuest(long document) throws Exception {
        GuestDto guestDto = new GuestDto();
        guestDto.setStatus(false);
    }

}
