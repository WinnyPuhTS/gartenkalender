package com.gartenkalender.app.data.database

import com.gartenkalender.app.data.model.Plant
import com.gartenkalender.app.data.model.PlantCategory
import com.gartenkalender.app.data.model.SunRequirement
import com.gartenkalender.app.data.model.WateringFrequency

/**
 * Embedded seed data – no network required.
 * Month arrays are stored as comma-padded strings (e.g. ",3,4,5,") to enable
 * unambiguous LIKE-based month queries in PlantDao.
 */
object PlantSeedData {

    fun getPlants(): List<Plant> = listOf(
        Plant(
            id = 1, name = "Tomate", nameLatin = "Solanum lycopersicum",
            category = PlantCategory.VEGETABLE,
            description = "Klassisches Sommergemüse, vielfältige Sorten.",
            sowingIndoorMonths = ",2,3,", sowingOutdoorMonths = "",
            plantingMonths = ",5,6,", harvestMonths = ",7,8,9,10,",
            spacingCm = 60, sunRequirement = SunRequirement.FULL_SUN,
            wateringFrequency = WateringFrequency.HIGH
        ),
        Plant(
            id = 2, name = "Gurke", nameLatin = "Cucumis sativus",
            category = PlantCategory.VEGETABLE,
            description = "Wärmeliebendes Gemüse für sonnige Beete.",
            sowingIndoorMonths = ",4,", sowingOutdoorMonths = ",5,",
            plantingMonths = ",5,6,", harvestMonths = ",7,8,9,",
            spacingCm = 50, sunRequirement = SunRequirement.FULL_SUN,
            wateringFrequency = WateringFrequency.HIGH
        ),
        Plant(
            id = 3, name = "Karotte", nameLatin = "Daucus carota",
            category = PlantCategory.VEGETABLE,
            description = "Robuste Wurzelgemüse, direkt säen.",
            sowingIndoorMonths = "", sowingOutdoorMonths = ",3,4,5,6,7,",
            plantingMonths = "", harvestMonths = ",6,7,8,9,10,11,",
            spacingCm = 5, sunRequirement = SunRequirement.FULL_SUN,
            wateringFrequency = WateringFrequency.MEDIUM
        ),
        Plant(
            id = 4, name = "Salat", nameLatin = "Lactuca sativa",
            category = PlantCategory.VEGETABLE,
            description = "Schnellwachsend, ideal für Anfänger.",
            sowingIndoorMonths = ",2,3,", sowingOutdoorMonths = ",3,4,5,8,9,",
            plantingMonths = ",3,4,5,9,", harvestMonths = ",4,5,6,7,10,11,",
            spacingCm = 25, sunRequirement = SunRequirement.PARTIAL_SHADE,
            wateringFrequency = WateringFrequency.MEDIUM
        ),
        Plant(
            id = 5, name = "Basilikum", nameLatin = "Ocimum basilicum",
            category = PlantCategory.HERB,
            description = "Aromatisches Küchenkraut, Wärme liebend.",
            sowingIndoorMonths = ",3,4,", sowingOutdoorMonths = ",5,",
            plantingMonths = ",5,6,", harvestMonths = ",6,7,8,9,",
            spacingCm = 20, sunRequirement = SunRequirement.FULL_SUN,
            wateringFrequency = WateringFrequency.MEDIUM
        ),
        Plant(
            id = 6, name = "Petersilie", nameLatin = "Petroselinum crispum",
            category = PlantCategory.HERB,
            description = "Zweijährige Pflanze, kältehart.",
            sowingIndoorMonths = ",2,3,", sowingOutdoorMonths = ",3,4,5,6,7,8,",
            plantingMonths = ",4,5,", harvestMonths = ",5,6,7,8,9,10,11,",
            spacingCm = 15, sunRequirement = SunRequirement.PARTIAL_SHADE,
            wateringFrequency = WateringFrequency.MEDIUM
        ),
        Plant(
            id = 7, name = "Schnittlauch", nameLatin = "Allium schoenoprasum",
            category = PlantCategory.HERB,
            description = "Mehrjährig, sehr pflegeleicht.",
            sowingIndoorMonths = ",2,3,", sowingOutdoorMonths = ",3,4,5,",
            plantingMonths = ",3,4,5,", harvestMonths = ",4,5,6,7,8,9,10,",
            spacingCm = 10, sunRequirement = SunRequirement.FULL_SUN,
            wateringFrequency = WateringFrequency.LOW
        ),
        Plant(
            id = 8, name = "Sonnenblume", nameLatin = "Helianthus annuus",
            category = PlantCategory.FLOWER,
            description = "Hochgewachsene Sommerblume, bienenfreundlich.",
            sowingIndoorMonths = ",3,4,", sowingOutdoorMonths = ",4,5,",
            plantingMonths = ",5,", harvestMonths = ",8,9,",
            spacingCm = 60, sunRequirement = SunRequirement.FULL_SUN,
            wateringFrequency = WateringFrequency.LOW
        ),
        Plant(
            id = 9, name = "Lavendel", nameLatin = "Lavandula angustifolia",
            category = PlantCategory.FLOWER,
            description = "Duftende Staude, mehrjährig und robust.",
            sowingIndoorMonths = ",1,2,3,", sowingOutdoorMonths = "",
            plantingMonths = ",4,5,9,", harvestMonths = ",6,7,8,",
            spacingCm = 40, sunRequirement = SunRequirement.FULL_SUN,
            wateringFrequency = WateringFrequency.LOW
        ),
        Plant(
            id = 10, name = "Zucchini", nameLatin = "Cucurbita pepo",
            category = PlantCategory.VEGETABLE,
            description = "Ertragreiche Sommerkürbisse.",
            sowingIndoorMonths = ",4,", sowingOutdoorMonths = ",5,",
            plantingMonths = ",5,6,", harvestMonths = ",7,8,9,10,",
            spacingCm = 100, sunRequirement = SunRequirement.FULL_SUN,
            wateringFrequency = WateringFrequency.HIGH
        ),
        Plant(
            id = 11, name = "Radieschen", nameLatin = "Raphanus sativus",
            category = PlantCategory.VEGETABLE,
            description = "Schnell reifend – ideal für Einsteiger.",
            sowingIndoorMonths = "", sowingOutdoorMonths = ",3,4,5,6,7,8,9,",
            plantingMonths = "", harvestMonths = ",4,5,6,7,8,9,10,",
            spacingCm = 5, sunRequirement = SunRequirement.PARTIAL_SHADE,
            wateringFrequency = WateringFrequency.MEDIUM
        ),
        Plant(
            id = 12, name = "Spinat", nameLatin = "Spinacia oleracea",
            category = PlantCategory.VEGETABLE,
            description = "Kältetolerant, zwei Saisons möglich.",
            sowingIndoorMonths = "", sowingOutdoorMonths = ",2,3,4,8,9,",
            plantingMonths = "", harvestMonths = ",4,5,6,9,10,11,",
            spacingCm = 10, sunRequirement = SunRequirement.PARTIAL_SHADE,
            wateringFrequency = WateringFrequency.MEDIUM
        ),
        Plant(
            id = 13, name = "Paprika", nameLatin = "Capsicum annuum",
            category = PlantCategory.VEGETABLE,
            description = "Lange Anzuchtzeit, wärmeliebendes Gemüse.",
            sowingIndoorMonths = ",1,2,3,", sowingOutdoorMonths = "",
            plantingMonths = ",5,6,", harvestMonths = ",8,9,10,",
            spacingCm = 40, sunRequirement = SunRequirement.FULL_SUN,
            wateringFrequency = WateringFrequency.MEDIUM
        ),
        Plant(
            id = 14, name = "Minze", nameLatin = "Mentha spicata",
            category = PlantCategory.HERB,
            description = "Mehrjährig, stark ausläuferbildend – am besten im Topf.",
            sowingIndoorMonths = ",3,4,", sowingOutdoorMonths = ",4,5,",
            plantingMonths = ",4,5,", harvestMonths = ",5,6,7,8,9,10,",
            spacingCm = 30, sunRequirement = SunRequirement.PARTIAL_SHADE,
            wateringFrequency = WateringFrequency.HIGH
        ),
        Plant(
            id = 15, name = "Ringelblume", nameLatin = "Calendula officinalis",
            category = PlantCategory.FLOWER,
            description = "Nützlingsfreundlich, essbare Blüten.",
            sowingIndoorMonths = ",3,", sowingOutdoorMonths = ",3,4,5,",
            plantingMonths = ",4,5,", harvestMonths = ",6,7,8,9,10,",
            spacingCm = 25, sunRequirement = SunRequirement.FULL_SUN,
            wateringFrequency = WateringFrequency.LOW
        )
    )
}
