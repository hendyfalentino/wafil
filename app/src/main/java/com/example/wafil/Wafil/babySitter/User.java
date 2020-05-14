package com.example.wafil.Wafil.babySitter;

    public class User {
        private String name;
        private String email;
        private String image;
        private String password;
        private String address;
        private String phone_number;
        private String gender;

        public User(String name, String email, String image, String password, String address, String phone_number, String gender) {
            this.name = name;
            this.email = email;
            this.image = image;
            this.password = password;
            this.address = address;
            this.phone_number = phone_number;
            this.gender = gender;
        }

        public String getName() { return name; }

        public String getEmail() { return email; }

        public String getImage() { return image; }

        public String getPassword() { return name; }

        public String getAddress() { return email; }

        public String getPhone_number() { return image; }

        public  String getGender() { return gender; }
    }
