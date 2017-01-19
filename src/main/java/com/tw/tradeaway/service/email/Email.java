package com.tw.tradeaway.service.email;

/**
 * Created by vikash on 17/01/17.
 */
public class Email {
    private String name;
    private String to;
    private String subject;
    private String text;

    public Email(String name, String to, String subject, String text) {
        this.name = name;
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Email email = (Email) o;

        if (name != null ? !name.equals(email.name) : email.name != null) return false;
        if (to != null ? !to.equals(email.to) : email.to != null) return false;
        if (subject != null ? !subject.equals(email.subject) : email.subject != null) return false;
        return text != null ? text.equals(email.text) : email.text == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (to != null ? to.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    public String getName() {
        return name;
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }
}
