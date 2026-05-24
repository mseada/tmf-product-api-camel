package com.mseada.tmf.processor;

import com.mseada.tmf.model.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * In-memory catalog service populated with Vodafone Egypt individual (RED)
 * postpaid rate plans sourced from www.vodafone.com.eg/en/vodafone-red1.
 *
 * <p>Plans included (all prices EGP/month, tax-exclusive):
 * <ul>
 *   <li>RED ESSENTIAL+ – 16 GB / 3 500 min – EGP 575</li>
 *   <li>RED ADVANCE+   – 32 GB / 6 000 min – EGP 980</li>
 *   <li>RED PRIME+     – 50 GB / 8 000 min – EGP 1 380</li>
 *   <li>RED ELITE+     – 85 GB / 10 000 min – EGP 2 000</li>
 *   <li>RED EXCLUSIVE  – 200 GB / 11 000 min – EGP 3 450</li>
 * </ul>
 *
 * <p>The catalog ({@code CAT-VFE-001}) groups all five offerings.
 * The {@link CatalogService} bean still handles generic catalog CRUD;
 * this service owns the Vodafone-specific catalog and product offerings.
 */
@Service("vodafoneEgyptCatalogService")
public class VodafoneEgyptCatalogService {

    private static final String CATALOG_ID   = "CAT-VFE-001";
    private static final String CATALOG_HREF = "/productCatalogManagement/v4/catalog/" + CATALOG_ID;
    private static final String OFFERING_BASE_HREF = "/productCatalogManagement/v4/productOffering/";
    private static final String SPEC_BASE_HREF     = "/productCatalogManagement/v4/productSpecification/";

    private final Catalog catalog;
    private final Map<String, ProductOffering> offeringsById;

    public VodafoneEgyptCatalogService() {
        TimePeriod activeForever = TimePeriod.builder()
                .startDateTime(OffsetDateTime.parse("2024-01-01T00:00:00+02:00"))
                .build();

        List<ProductOffering> offerings = List.of(
                buildOffering("PO-VFE-001", "RED ESSENTIAL+",
                        "Vodafone Egypt RED ESSENTIAL+ – entry-level postpaid plan with 16 GB data, "
                        + "3 500 minutes to any network, and entertainment subscriptions.",
                        "1.0", 16, 3500,
                        new BigDecimal("575.00"),
                        List.of("WATCH IT", "OSN+", "Anghami Plus", "Amazon Prime", "Yango Play"),
                        1, 140, 40, false, false, 0, activeForever),

                buildOffering("PO-VFE-002", "RED ADVANCE+",
                        "Vodafone Egypt RED ADVANCE+ – mid-range postpaid plan with 32 GB data, "
                        + "6 000 minutes to any network, Talabat Pro and roaming 1 week/year.",
                        "1.0", 32, 6000,
                        new BigDecimal("980.00"),
                        List.of("Disney+", "WATCH IT", "OSN+", "Anghami Plus",
                                "Amazon Prime", "Yango Play", "TOD"),
                        2, 140, 40, true, false, 1, activeForever),

                buildOffering("PO-VFE-003", "RED PRIME+",
                        "Vodafone Egypt RED PRIME+ – premium postpaid plan with 50 GB data, "
                        + "8 000 minutes to any network, YouTube Premium and roaming 2 weeks/year.",
                        "1.0", 50, 8000,
                        new BigDecimal("1380.00"),
                        List.of("Disney+", "YouTube Premium", "WATCH IT", "OSN+",
                                "Anghami Plus", "Amazon Prime", "Yango Play", "TOD"),
                        3, 200, 135, true, false, 2, activeForever),

                buildOffering("PO-VFE-004", "RED ELITE+",
                        "Vodafone Egypt RED ELITE+ – high-tier postpaid plan with 85 GB data, "
                        + "10 000 minutes, all streaming apps, and roaming 3 weeks/year.",
                        "1.0", 85, 10000,
                        new BigDecimal("2000.00"),
                        List.of("Disney+", "YouTube Premium", "WATCH IT", "OSN+",
                                "Anghami Plus", "Amazon Prime", "Yango Play", "TOD"),
                        5, 300, 135, true, true, 3, activeForever),

                buildOffering("PO-VFE-005", "RED EXCLUSIVE",
                        "Vodafone Egypt RED EXCLUSIVE – flagship postpaid plan with 200 GB data, "
                        + "11 000 minutes, all 8 entertainment apps, roaming 4 weeks/year and 60 international minutes.",
                        "1.0", 200, 11000,
                        new BigDecimal("3450.00"),
                        List.of("Disney+", "YouTube Premium", "WATCH IT", "OSN+",
                                "Anghami Plus", "Amazon Prime", "Yango Play", "TOD"),
                        6, 600, 135, true, true, 4, activeForever)
        );

        this.offeringsById = offerings.stream()
                .collect(Collectors.toMap(ProductOffering::getId, Function.identity()));

        this.catalog = Catalog.builder()
                .id(CATALOG_ID)
                .href(CATALOG_HREF)
                .name("Vodafone Egypt Individual Plans")
                .description("Vodafone Egypt RED postpaid rate plans for individual customers. "
                        + "Source: www.vodafone.com.eg/en/vodafone-red1")
                .catalogType("ProductCatalog")
                .lifecycleStatus("Active")
                .version("1.0")
                .validFor(OffsetDateTime.parse("2024-01-01T00:00:00+02:00"))
                .category(List.of(
                        CategoryRef.builder()
                                .id("CAT-INDIVIDUAL")
                                .name("Individual Plans")
                                .atReferredType("CategoryRef")
                                .build()
                ))
                .relatedParty(List.of(
                        RelatedParty.builder()
                                .id("RP-VFE-001")
                                .name("Vodafone Egypt")
                                .role("Owner")
                                .atReferredType("Organization")
                                .build()
                ))
                .build();
    }

    // ── Public API ───────────────────────────────────────────────────────────

    public Catalog getCatalog() {
        return catalog;
    }

    public List<ProductOffering> listOfferings() {
        return List.copyOf(offeringsById.values());
    }

    public List<ProductOffering> listOfferingsByCategory(String category) {
        return offeringsById.values().stream()
                .filter(o -> category == null || category.isBlank()
                        || category.equalsIgnoreCase(o.getCategory()))
                .collect(Collectors.toList());
    }

    public Optional<ProductOffering> findOfferingById(String id) {
        return Optional.ofNullable(offeringsById.get(id));
    }

    // ── Builder helpers ──────────────────────────────────────────────────────

    private ProductOffering buildOffering(
            String id,
            String name,
            String description,
            String version,
            int dataGb,
            int voiceMinutes,
            BigDecimal priceEgp,
            List<String> entertainmentApps,
            int familyMembers,
            int adslGb,
            int homeWirelessGb,
            boolean hasTalabatPro,
            boolean hasRoaming,
            int roamingWeeks,
            TimePeriod validFor) {

        String specId  = id.replace("PO-", "PS-");
        String priceId = id.replace("PO-", "PP-");

        ProductSpecification spec = ProductSpecification.builder()
                .id(specId)
                .href(SPEC_BASE_HREF + specId)
                .name(name + " Specification")
                .description("Technical specification for " + name)
                .version(version)
                .lifecycleStatus("Active")
                .validFor(validFor)
                .productSpecCharacteristic(List.of(
                        characteristic("dataAllowance",
                                "Mobile data allowance included in the plan",
                                dataGb + " GB", "GB"),
                        characteristic("voiceMinutes",
                                "Voice call minutes to any Egyptian network",
                                voiceMinutes + " min", "minutes"),
                        characteristic("entertainmentApps",
                                "Included streaming and entertainment subscriptions",
                                String.join(", ", entertainmentApps), null),
                        characteristic("familySharing",
                                "Maximum additional family members that can share this plan",
                                familyMembers + " members", "members"),
                        characteristic("adslBundle",
                                "Home ADSL data bundle (up to 30 Mbps)",
                                adslGb + " GB", "GB"),
                        characteristic("homeWireless",
                                "Home Wireless data bundle",
                                homeWirelessGb + " GB", "GB"),
                        characteristic("talabatPro",
                                "Complimentary Talabat Pro food delivery subscription",
                                hasTalabatPro ? "Included" : "Not included", null),
                        characteristic("roaming",
                                hasRoaming
                                        ? "Take your bundle abroad for " + roamingWeeks + " week(s) per year"
                                        : "Roaming not included",
                                hasRoaming ? roamingWeeks + " weeks/year" : "None", null)
                ))
                .build();

        ProductOfferingPrice price = ProductOfferingPrice.builder()
                .id(priceId)
                .href(OFFERING_BASE_HREF + priceId)
                .name(name + " Monthly Price")
                .description("Monthly recurring charge for " + name + " (tax exclusive)")
                .priceType("recurring")
                .recurringChargePeriodType("monthly")
                .lifecycleStatus("Active")
                .validFor(validFor)
                .price(Money.builder()
                        .value(priceEgp)
                        .unit("EGP")
                        .build())
                .build();

        return ProductOffering.builder()
                .id(id)
                .href(OFFERING_BASE_HREF + id)
                .name(name)
                .description(description)
                .version(version)
                .lifecycleStatus("Active")
                .isSellable(true)
                .category("individual")
                .validFor(validFor)
                .channel(List.of("online", "store", "app"))
                .productOfferingPrice(List.of(price))
                .productSpecification(spec)
                .build();
    }

    private ProductSpecificationCharacteristic characteristic(
            String name, String description, String value, String unit) {

        ProductSpecificationCharacteristicValue charVal =
                ProductSpecificationCharacteristicValue.builder()
                        .valueType("string")
                        .value(value)
                        .unitOfMeasure(unit)
                        .isDefault(true)
                        .build();

        return ProductSpecificationCharacteristic.builder()
                .name(name)
                .description(description)
                .valueType("string")
                .productSpecCharacteristicValue(List.of(charVal))
                .build();
    }
}
