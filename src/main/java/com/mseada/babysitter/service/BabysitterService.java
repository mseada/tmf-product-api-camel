package com.mseada.babysitter.service;

import com.mseada.babysitter.model.Babysitter;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BabysitterService {

    private final Map<String, Babysitter> store = new LinkedHashMap<>();

    @PostConstruct
    public void init() {
        List<Babysitter> samples = Arrays.asList(
            new Babysitter(
                "bs001", "Nadia Mahmoud", null, 28, 5, 4.8, 450.0, 2800.0, 9500.0,
                "Maadi",
                Arrays.asList("Arabic", "English"),
                Arrays.asList("Newborn Care", "First Aid", "Educational Activities", "Meal Preparation"),
                "Available",
                "I am a dedicated and experienced babysitter based in Maadi. I hold a degree in Early Childhood Education and have cared for children of all ages from newborns to 10-year-olds. I believe in nurturing creativity and building healthy routines. Families in Maadi and Degla have trusted me for over five years.",
                Arrays.asList("CPR & First Aid Certified", "Early Childhood Education Diploma", "Pediatric Nutrition Certificate"),
                47, "+20 100 123 4567", "nadia.mahmoud@example.com"
            ),
            new Babysitter(
                "bs002", "Salma Hassan", null, 24, 2, 4.2, 280.0, 1700.0, 6000.0,
                "Zamalek",
                Arrays.asList("Arabic", "English", "French"),
                Arrays.asList("Homework Help", "Arts & Crafts", "Storytelling", "Swimming Supervision"),
                "Available",
                "Energetic and creative babysitter living in Zamalek. I studied French literature and love bringing fun learning experiences to children. Fluent in three languages, I can help children with homework across subjects and love organizing creative afternoons.",
                Arrays.asList("CPR Certified", "Lifeguard Training"),
                19, "+20 111 234 5678", "salma.hassan@example.com"
            ),
            new Babysitter(
                "bs003", "Dina Kamal", null, 35, 10, 5.0, 750.0, 4800.0, 18000.0,
                "Heliopolis",
                Arrays.asList("Arabic", "English"),
                Arrays.asList("Newborn Care", "Special Needs", "Montessori Activities", "First Aid", "Cooking"),
                "Available",
                "With 10 years of experience and a background in special education, I offer premium childcare in Heliopolis and surrounding areas. I specialize in children with developmental needs and hold multiple certifications. My approach is patient, structured, and full of love.",
                Arrays.asList("Special Education Diploma", "CPR & First Aid Advanced", "Montessori Certified", "Pediatric Psychology Certificate"),
                93, "+20 122 345 6789", "dina.kamal@example.com"
            ),
            new Babysitter(
                "bs004", "Rana Ibrahim", null, 22, 1, 3.9, 220.0, 1350.0, 4800.0,
                "Nasr City",
                Arrays.asList("Arabic"),
                Arrays.asList("Play Activities", "Meal Preparation", "School Pickup"),
                "Available",
                "I am a university student studying Education, offering part-time babysitting in Nasr City. I love working with children and bring lots of energy and enthusiasm to every session. Currently building my portfolio and learning new childcare techniques.",
                Arrays.asList("Basic First Aid Training"),
                8, "+20 100 456 7890", "rana.ibrahim@example.com"
            ),
            new Babysitter(
                "bs005", "Mariam Saad", null, 30, 7, 4.6, 550.0, 3400.0, 12500.0,
                "New Cairo",
                Arrays.asList("Arabic", "English"),
                Arrays.asList("Newborn Care", "Educational Activities", "First Aid", "Yoga for Kids", "Meal Preparation"),
                "Busy",
                "Experienced nanny serving New Cairo and Fifth Settlement families. With 7 years of childcare experience, I specialize in newborns and toddlers. I hold a nursing background which gives parents extra peace of mind. Currently available for monthly engagements only.",
                Arrays.asList("Registered Nurse Certificate", "CPR & First Aid", "Child Psychology Workshop"),
                62, "+20 115 567 8901", "mariam.saad@example.com"
            ),
            new Babysitter(
                "bs006", "Yasmine Farouk", null, 26, 4, 4.5, 380.0, 2400.0, 8500.0,
                "Dokki",
                Arrays.asList("Arabic", "English", "German"),
                Arrays.asList("Educational Activities", "Arts & Crafts", "Language Learning", "Homework Help"),
                "Available",
                "Trilingual babysitter based in Dokki, offering enriching childcare experiences. I have a background in linguistics and love introducing children to new languages through games and stories. Ideal for families who value educational development alongside daily care.",
                Arrays.asList("TESOL Certificate", "CPR Certified", "Child Development Workshop"),
                35, "+20 106 678 9012", "yasmine.farouk@example.com"
            ),
            new Babysitter(
                "bs007", "Hana Mostafa", null, 32, 8, 4.7, 600.0, 3800.0, 14000.0,
                "Maadi",
                Arrays.asList("Arabic", "English"),
                Arrays.asList("Newborn Care", "Special Needs", "Sleep Training", "Meal Preparation", "First Aid"),
                "Available",
                "Highly experienced babysitter in Maadi with 8 years caring for families in the area. My specialty is sleep training for infants and supporting children with sensory sensitivities. Parents appreciate my calm, professional demeanor and detailed daily reports.",
                Arrays.asList("CPR & First Aid Advanced", "Sleep Training Certification", "Sensory Processing Disorder Workshop"),
                71, "+20 128 789 0123", "hana.mostafa@example.com"
            ),
            new Babysitter(
                "bs008", "Layla Ahmed", null, 27, 3, 4.1, 300.0, 1850.0, 6500.0,
                "6th of October",
                Arrays.asList("Arabic", "English"),
                Arrays.asList("Arts & Crafts", "Storytelling", "Play Activities", "Homework Help"),
                "Available",
                "Fun and nurturing babysitter serving 6th of October City. I hold a Fine Arts background and love sparking creativity in children through art projects, storytelling, and imaginative play. Three years of consistent experience with families who value creative development.",
                Arrays.asList("Basic First Aid", "Child Art Therapy Workshop"),
                27, "+20 101 890 1234", "layla.ahmed@example.com"
            ),
            new Babysitter(
                "bs009", "Noura Adel", null, 29, 6, 4.9, 680.0, 4300.0, 16000.0,
                "Zamalek",
                Arrays.asList("Arabic", "English", "Italian"),
                Arrays.asList("Newborn Care", "Educational Activities", "Montessori Activities", "Meal Preparation", "First Aid", "Music for Kids"),
                "Available",
                "Premium childcare specialist in Zamalek with 6 years of experience serving expatriate and Egyptian families. I hold Montessori certification and offer structured, play-based learning environments. Multilingual capability (Arabic, English, Italian) makes me ideal for international families.",
                Arrays.asList("Montessori Level 1 & 2 Certified", "CPR & First Aid Advanced", "Music & Movement for Early Childhood"),
                84, "+20 109 901 2345", "noura.adel@example.com"
            ),
            new Babysitter(
                "bs010", "Sara Naguib", null, 23, 2, 3.8, 230.0, 1400.0, 5000.0,
                "Heliopolis",
                Arrays.asList("Arabic", "English"),
                Arrays.asList("Homework Help", "Play Activities", "School Pickup", "Meal Preparation"),
                "Busy",
                "Enthusiastic babysitter in Heliopolis, currently completing a Child Psychology degree. I offer affordable childcare for school-age children including after-school pickup, homework assistance, and preparing healthy snacks. Building experience with the hope of growing into a full-time childcare professional.",
                Arrays.asList("Basic First Aid Training"),
                14, "+20 120 012 3456", "sara.naguib@example.com"
            )
        );

        for (Babysitter b : samples) {
            store.put(b.getId(), b);
        }
    }

    public List<Babysitter> findAll() {
        return new ArrayList<>(store.values());
    }

    public Babysitter findById(String id) {
        return store.get(id);
    }

    public List<Babysitter> search(String query, Double minRating, String availability, String area) {
        return store.values().stream()
            .filter(b -> {
                if (query != null && !query.isBlank()) {
                    String q = query.toLowerCase();
                    boolean matchesName = b.getName().toLowerCase().contains(q);
                    boolean matchesArea = b.getArea().toLowerCase().contains(q);
                    if (!matchesName && !matchesArea) return false;
                }
                return true;
            })
            .filter(b -> minRating == null || b.getRating() >= minRating)
            .filter(b -> availability == null || availability.isBlank() || b.getAvailability().equalsIgnoreCase(availability))
            .filter(b -> area == null || area.isBlank() || b.getArea().equalsIgnoreCase(area))
            .collect(Collectors.toList());
    }

    public List<String> findAllAreas() {
        return store.values().stream()
            .map(Babysitter::getArea)
            .distinct()
            .sorted()
            .collect(Collectors.toList());
    }
}
