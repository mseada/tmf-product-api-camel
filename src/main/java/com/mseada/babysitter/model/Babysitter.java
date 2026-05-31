package com.mseada.babysitter.model;

import java.util.List;

public class Babysitter {
    private String id;
    private String name;
    private String photo;
    private int age;
    private int experienceYears;
    private double rating;
    private double dailyRate;
    private double weeklyRate;
    private double monthlyRate;
    private String area;
    private List<String> languages;
    private List<String> skills;
    private String availability;
    private String bio;
    private List<String> certifications;
    private int reviewCount;
    private String phone;
    private String email;

    public Babysitter() {}

    public Babysitter(String id, String name, String photo, int age, int experienceYears,
                      double rating, double dailyRate, double weeklyRate, double monthlyRate,
                      String area, List<String> languages, List<String> skills,
                      String availability, String bio, List<String> certifications,
                      int reviewCount, String phone, String email) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.age = age;
        this.experienceYears = experienceYears;
        this.rating = rating;
        this.dailyRate = dailyRate;
        this.weeklyRate = weeklyRate;
        this.monthlyRate = monthlyRate;
        this.area = area;
        this.languages = languages;
        this.skills = skills;
        this.availability = availability;
        this.bio = bio;
        this.certifications = certifications;
        this.reviewCount = reviewCount;
        this.phone = phone;
        this.email = email;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhoto() { return photo; }
    public void setPhoto(String photo) { this.photo = photo; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public int getExperienceYears() { return experienceYears; }
    public void setExperienceYears(int experienceYears) { this.experienceYears = experienceYears; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public double getDailyRate() { return dailyRate; }
    public void setDailyRate(double dailyRate) { this.dailyRate = dailyRate; }

    public double getWeeklyRate() { return weeklyRate; }
    public void setWeeklyRate(double weeklyRate) { this.weeklyRate = weeklyRate; }

    public double getMonthlyRate() { return monthlyRate; }
    public void setMonthlyRate(double monthlyRate) { this.monthlyRate = monthlyRate; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    public List<String> getLanguages() { return languages; }
    public void setLanguages(List<String> languages) { this.languages = languages; }

    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) { this.skills = skills; }

    public String getAvailability() { return availability; }
    public void setAvailability(String availability) { this.availability = availability; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public List<String> getCertifications() { return certifications; }
    public void setCertifications(List<String> certifications) { this.certifications = certifications; }

    public int getReviewCount() { return reviewCount; }
    public void setReviewCount(int reviewCount) { this.reviewCount = reviewCount; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
