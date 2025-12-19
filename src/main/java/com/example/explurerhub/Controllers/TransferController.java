package com.example.explurerhub.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TransferController {
    @GetMapping("/about")
    public String showAbout() {
        return "about";
    }

    @GetMapping("/where-to-go")
    public String showWhereToGo() {
        return "where-to-go";
    }

    @GetMapping("/what-to-do")
    public String showWhatToDo() {
        return "what-to-do";
    }

    @GetMapping("/whats-on")
    public String showWhatsOn() {
        return "whats-on";
    }

    @GetMapping("/useful-info")
    public String showUsefulInfo() {
        return "useful-info";
    }

    @GetMapping("/cities")
    public String showCities() {
        return "cities";
    }

    @GetMapping("/nile")
    public String showNile() {
        return "nile";
    }

    @GetMapping("/red-sea")
    public String showRedSea() {
        return "red-sea";
    }

    @GetMapping("/med")
    public String showMed() {
        return "med";
    }

    @GetMapping("/deserts")
    public String showDeserts() {
        return "deserts";
    }

    @GetMapping("/sun-sea")
    public String showSunSea() {
        return "sun-and-sea";
    }

    @GetMapping("/abu-simble")
    public String showAbuSimble() {
        return "abu-simble";
    }
    @GetMapping("/old-alexandria")
    public String showOldAlexandria() {
        return "alexandria-old";
    }
    @GetMapping("/aswan-city")
    public String showAswanCity() {
        return "aswan-city";
    }
    @GetMapping("/aswan-food")
    public String showAswanFood() {
        return "aswan-food";
    }
    @GetMapping("/aswan-mosques")
    public String showAswanMosques() {
        return "aswan-mosques";
    }
    @GetMapping("/aswan-museums")
    public String showAswanMuseums() {
        return "aswan-museums";
    }
    @GetMapping("/old-aswan")
    public String showOldAswan() {
        return "aswan-old";
    }
    @GetMapping("/bahariya")
    public String showBahariya() {
        return "bahariya-city";
    }
    @GetMapping("/bahariya-hotels")
    public String showBahariyaHotels() {
        return "bahariya-hotels";
    }
    @GetMapping("/bahariya-transport")
    public String showBahariyaTransport() {
        return "bahariya-transport";
    }
    @GetMapping("/bibliotheca")
    public String showBibliotheca() {
        return "bibliotheca";
    }
    @GetMapping("/cairo-food")
    public String showCairoFood() {
        return "cairo-food";
    }
    @GetMapping("/cairo-hotels")
    public String showCairoHotels() {
        return "cairo-hotels";
    }
    @GetMapping("/cairo")
    public String showCairoMosques() {
        return "cairo";
    }

    @GetMapping("/old-cairo")
    public String showOldCairo() {
        return "cairo-old";
    }


    @GetMapping("/cairo-tours")
    public String showCairoTours() {
        return "cairo-tours";
    }
    @GetMapping("/cairo-transport")
    public String showCairoTransport() {
        return "cairo-transport";
    }
    @GetMapping("/citadel-qaitbay")
    public String showCitadelQaitbay() {
        return "citadel-qaitbay";
    }
    @GetMapping("/dahab")
    public String showDahab() {
        return "dahab";
    }
    @GetMapping("/dahab-tours")
    public String showDahabTours() {
        return "dahab-tours";
    }
    @GetMapping("/dahab-transport")
    public String showDahabTransport() {
        return "dahab-transport";
    }

    @GetMapping("/dakhla-kharga")
    public String showDakhlaKharga() {
        return "dakhla-kharga";
    }
    @GetMapping("/dakhla-kharga-tours")
    public String showDakhlaKhargaTours() {
        return "dakhla-kharga-tours";
    }
    @GetMapping("/dakhla-kharga-transport")
    public String showDakhlaKhargaTransport() {
        return "dakhla-kharga-transport";
    }

    @GetMapping("/edfu-kom-ombo")
    public String showEdfuKomOmbo() {
        return "edfu-kom-ombo";
    }

    // Giza City Pages
    @GetMapping("/giza")
    public String showGiza() {
        return "giza";
    }

    @GetMapping("/giza-museums")
    public String showGizaMuseums() {
        return "giza-museums";
    }

    @GetMapping("/giza-mosques")
    public String showGizaMosques() {
        return "giza-mosques";
    }

    @GetMapping("/giza-old")
    public String showGizaOld() {
        return "giza-old";
    }

    @GetMapping("/giza-nile")
    public String showGizaNile() {
        return "giza-nile";
    }

    // Hurghada Pages
    @GetMapping("/hurghada")
    public String showHurghada() {
        return "hurghada";
    }

    @GetMapping("/hurghada-city")
    public String showHurghadaCity() {
        return "hurghada-city";
    }

    @GetMapping("/hurghada-food")
    public String showHurghadaFood() {
        return "hurghada-food";
    }

    @GetMapping("/hurghada-hotels")
    public String showHurghadaHotels() {
        return "hurghada-hotels";
    }

    @GetMapping("/hurghada-mosques")
    public String showHurghadaMosques() {
        return "hurghada-mosques";
    }

    @GetMapping("/hurghada-museums")
    public String showHurghadaMuseums() {
        return "hurghada-museums";
    }

    @GetMapping("/hurghada-nile")
    public String showHurghadaNile() {
        return "hurghada-nile";
    }

    @GetMapping("/hurghada-old")
    public String showHurghadaOld() {
        return "hurghada-old";
    }

    @GetMapping("/hurghada-tours")
    public String showHurghadaTours() {
        return "hurghada-tours";
    }

    @GetMapping("/hurghada-transport")
    public String showHurghadaTransport() {
        return "hurghada-transport";
    }

    // Luxor Pages
    @GetMapping("/luxor-city")
    public String showLuxorCity() {
        return "luxor-city";
    }

    @GetMapping("/luxor-food")
    public String showLuxorFood() {
        return "luxor-food";
    }

    @GetMapping("/luxor-karnak")
    public String showLuxorKarnak() {
        return "luxor-karnak";
    }

    @GetMapping("/luxor-mosques")
    public String showLuxorMosques() {
        return "luxor-mosques";
    }

    @GetMapping("/luxor-museums")
    public String showLuxorMuseums() {
        return "luxor-museums";
    }

    @GetMapping("/luxor-nile")
    public String showLuxorNile() {
        return "luxor-nile";
    }
    @GetMapping("/marsa-alam")
    public String showMarsaAlam() {
        return "marsa-alam";
    }


    @GetMapping("/montaza")
    public String showMontaza() {
        return "montaza";
    }



    @GetMapping("/north-coast")
    public String showNorthCoast() {
        return "north-coast";
    }


    @GetMapping("/sharm-city")
    public String showSharmCity() {
        return "sharm-city";
    }

    @GetMapping("/sharm-el-sheikh")
    public String showSharmElSheikh() {
        return "sharm-el-sheikh";
    }

    @GetMapping("/sharm-food")
    public String showSharmFood() {
        return "sharm-food";
    }

    @GetMapping("/sharm-hotels")
    public String showSharmHotels() {
        return "sharm-hotels";
    }

    @GetMapping("/sharm-mosques")
    public String showSharmMosques() {
        return "sharm-mosques";
    }

    @GetMapping("/sharm-museums")
    public String showSharmMuseums() {
        return "sharm-museums";
    }

    @GetMapping("/sharm-nile")
    public String showSharmNile() {
        return "sharm-nile";
    }

    @GetMapping("/sharm-old")
    public String showSharmOld() {
        return "sharm-old";
    }

    @GetMapping("/sharm-tours")
    public String showSharmTours() {
        return "sharm-tours";
    }

    @GetMapping("/sharm-transport")
    public String showSharmTransport() {
        return "sharm-transport";
    }
    @GetMapping("/siwa")
    public String showSiwa() {
        return "siwa";
    }

    @GetMapping("/siwa-city")
    public String showSiwaCity() {
        return "siwa-city";
    }

    @GetMapping("/siwa-hotels")
    public String showSiwaHotels() {
        return "siwa-hotels";
    }

    @GetMapping("/siwa-tours")
    public String showSiwaTours() {
        return "siwa-tours";
    }

    @GetMapping("/siwa-transport")
    public String showSiwaTransport() {
        return "siwa-transport";
    }

    @GetMapping("/spiritual-egypt")
    public String showSpiritualEgypt() {
        return "spiritual-egypt";
    }

    @GetMapping("/sun-and-sea")
    public String showSunAndSea() {
        return "sun-and-sea";
    }

    @GetMapping("/useful-info-page")
    public String showUsefulInfoPage() {
        return "useful-info";
    }

    @GetMapping("/valley-of-the-kings")
    public String showValleyOfTheKings() {
        return "valley-of-the-kings";
    }

    @GetMapping("/what-to-do-page")
    public String showWhatToDoPage() {
        return "what-to-do";
    }

    @GetMapping("/whats-on-page")
    public String showWhatsOnPage() {
        return "whats-on";
    }

    @GetMapping("/where-to-go-page")
    public String showWhereToGoPage() {
        return "where-to-go";
    }

    @GetMapping("/white-desert")
    public String showWhiteDesert() {
        return "white-desert";
    }
}

