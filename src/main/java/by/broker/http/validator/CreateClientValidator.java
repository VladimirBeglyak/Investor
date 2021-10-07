package by.broker.http.validator;


import by.broker.http.dto.CreateClientDto;
import by.broker.http.entity.Role;
import by.broker.http.util.LocaleDateFormatter;

public class CreateClientValidator implements Validator<CreateClientDto> {

    private static final CreateClientValidator INSTANCE=new CreateClientValidator();
    private CreateClientValidator(){}

    public static CreateClientValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public ValidationResult isValid(CreateClientDto object) {

        ValidationResult validationResult = new ValidationResult();

        if (object.getRole()==null || Role.valueOf(object.getRole())==null){
            validationResult.add(Error.of("role_error","role is invalid"));
        }

        if (!LocaleDateFormatter.isValid(object.getBirthday())){
            validationResult.add(Error.of("date_error","Birthday is invalid"));
        }

        if (object.getEmail()==null){
            validationResult.add(Error.of("password_error","You need enter the password"));
        }

        return validationResult;
    }
}
