package howmanycals.utils;

import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import howmanycals.domain.email.MealEmail;
import java.io.IOException;

public final class EmailSenderUtil {
    
    private EmailSenderUtil() {}
    
    public static Response send(final MealEmail email) throws IOException {
        final String apiKey = System.getenv(Constants.EMAIL_API_KEY);
        if (apiKey == null || apiKey.isBlank()) {
            throw new IOException("SENDGRID_API_KEY environment variable not defined");
        }
        
        final Mail mail = new Mail(email.from(), email.getSubject(), email.to(), email.content());
        final SendGrid sg = new SendGrid(apiKey);
        
        final Request request = new Request();
        
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        
        return sg.api(request);
    }
    
}
