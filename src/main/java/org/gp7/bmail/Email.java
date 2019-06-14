package org.gp7.bmail;

public class Email {
    private String subject;
    private String from;
    private String content;

    public Email(String subject, String from, String content) {
        this.subject = subject;
        this.from = from;
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public String getFrom() {
        return from;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "------------------------------------------" + "\n" +
                "Subject: " + subject + "\n" +
                "From: " + from + "\n" +
                "------------------------------------------" + "\n" +
                content;
    }
}

