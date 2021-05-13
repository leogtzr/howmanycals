package howmanycals.domain.email;

import com.sendgrid.*;

public class MealEmail {
    private final Email from;
    private final String subject;
    private final Email to;
    private final Content content;
    
    private MealEmail(final Builder builder) {
        this.from = builder.from;
        this.subject = builder.subject;
        this.to = builder.to;
        this.content = builder.content;
    }
    
    public static class Builder {
        private Email from;
        private String subject;
        private Email to;
        private Content content;
        
        public Builder from(final String from) {
            this.from = new Email(from);
            return this;
        }
        
        public Builder to(final String to) {
            this.to = new Email(to);
            return this;
        }
        
        public Builder subject(final String subject) {
            this.subject = subject;
            return this;
        }
        
        public Builder content(final String type, final String content) {
            this.content = new Content(type, content);
            return this;
        }
        
        public MealEmail build() {
            return new MealEmail(this);
        }
    }

    public Email from() {
        return this.from;
    }

    public String getSubject() {
        return this.subject;
    }

    public Email to() {
        return this.to;
    }

    public Content content() {
        return content;
    }

    @Override
    public String toString() {
        return "Email{" + "from=" + from + ", subject=" + subject + ", to=" + to + ", content=" + content + '}';
    }    
}
