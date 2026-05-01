package exceptions;

import core.User;

public class NotResearcherException extends Exception{
    public NotResearcherException(User user) {
        super("User " + user.getLogin() + " is not a researcher");
    }
    public NotResearcherException(String message) {
        super(message);
    }
}
