package app.controller;

import app.controllervalidator.PartnerValidator;
import app.controllervalidator.PersonValidator;
import app.controllervalidator.UserValidator;
import app.dto.GuestDto;
import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.dto.UserDto;
import app.service.Service;
import app.serviceinterfaces.PartnerService;

public class PartnerController implements ControllerInterface  {
    private PersonValidator personValidator;
    private UserValidator userValidator;
    private PartnerValidator partnerValidator;
    private PartnerService service;
    
    private static final String MENU = "ingrese la opcion que desea ejecutar: \n 1. para crear invitado. \n 2. activar invitado. \n 3. para desactivar invitado. \n 4. hacer consumo. \n 5. subir fondos. \n 6. solicitar baja. \n 7. solicitar promocion. \n 8. cerrrar sesion." ;


    public PartnerController() {
        super();
        this.personValidator = new PersonValidator();
        this.userValidator = new UserValidator();
        this.partnerValidator = new PartnerValidator();
        this.service = new Service();
    }

    public void session() throws Exception {
        boolean session = true;
        while (session) {
            session = menu();
            }
	}

    private boolean menu() {
	try {
            System.out.println(MENU);
            String option = Utils.getReader().nextLine();
            return this.options(option);
            } catch (Exception e) {
		System.out.println(e.getMessage());
		return true;
        }
    }

  	private boolean options(String option) throws Exception {
		switch (option) {
		case "1": {
			this.CreateGuest();
			return true;
		}
		case "2": {
			this.ActivateGuest();
			return true;
		}
		case "3": {
			this.DesactivateGuest();
			return true;
		}
		case "4": {
			this.MakeConsumption();
			return true;
		}
                case "5": {
			this.UploadFunds();
			return true;
                }
                case "6": {
			this.RequestCancelation();
			return true;
                }
                case "7": {
			this.RequestVip();
			return true;
                }
                
		case "8": {
			System.out.println("Se ha cerrado sesion");
			return false;
		}
		default: {
			System.out.println("ingrese una opcion valida");
			return true;
                }
        }
    }
    
    private void CreateGuest() throws Exception{
        System.out.println("ingrese el nombre");
            String name = Utils.getReader().nextLine();
            personValidator.validName(name);
            System.out.println("ingrese la cedula");
            long document = personValidator.validDocument(Utils.getReader().nextLine());
            System.out.println("ingrese el numero de telefono celular");
            long celPhone = personValidator.validCellphone(Utils.getReader().nextLine());
            System.out.println("ingrese el nombre de usuario");
            String userName = Utils.getReader().nextLine();
            userValidator.validUserName(userName);
            System.out.println("ingrese la contraseña");
            String password = Utils.getReader().nextLine();
            userValidator.validPassword(password);

            PersonDto personDto = new PersonDto();
            personDto.setName(name);
            personDto.setDocument(document);
            personDto.setCelphone(celPhone);
            UserDto userDto = new UserDto();
            userDto.setPersonId(personDto);
            userDto.setUserName(userName);
            userDto.setPassword(password);
            userDto.setRole("guest");
            GuestDto guestDto = new GuestDto();
            guestDto.setUserId(userDto);
            guestDto.setStatus(true);
            this.service.createGuest(userDto);
            System.out.println("se ha creado el usuario exitosamente");
  
    }
    
    private void VipPromotion() throws Exception{
        System.out.println("Ascender socio regular a VIP");    
            PartnerDto partnerDto = new PartnerDto();
            partnerDto.setType(true);
    }
    
    private void DesactivateGuest()throws Exception{
        System.out.println("desactivar invitado");
        System.out.println("numero de cedula del invitado");
        long document = personValidator.validDocument(Utils.getReader().nextLine());
        this.service.desactivateGuest(document);
    }
    private void ActivateGuest()throws Exception{
        System.out.println("desactivar invitado"); 
    }
       private void MakeConsumption() throws Exception {
	}

    private void UploadFunds() throws Exception{
        System.out.println("Ingrese el monto que desea aumentar");
            double amount = Utils.getReader().nextDouble();
     
            PartnerDto partnerDto = new PartnerDto();
            partnerDto.setAmount(amount);{
    }
    }
           private void RequestCancelation() throws Exception {
           }

          
           private void RequestVip() throws Exception {
	}

}

    
    
