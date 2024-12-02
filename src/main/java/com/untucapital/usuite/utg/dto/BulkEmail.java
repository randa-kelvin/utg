package com.untucapital.usuite.utg.dto;

    public class BulkEmail {
        private String[] recipients;
        private String subject;
        private String message;

        // Constructor, getters, and setters

        public String[] getRecipients() {
            return recipients;
        }

        public void setRecipients(String[] recipients) {
            this.recipients = recipients;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

//}
