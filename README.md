# Završni Projekat - Automatizacija Testiranja (DemoQA Forms)

Ovaj projekat predstavlja završni rad na ITBootcamp obuci za QA Inženjera. Fokus projekta je automatizovano end-to-end (E2E) testiranje forme za registraciju na sajtu [DemoQA](https://demoqa.com/automation-practice-form).

## 📋 QA Dokumentacija (Manuelno Testiranje)

Pored automatizovanih testova, projekat sadrži i prateću QA dokumentaciju kreiranu u Excelu, koja pokriva proces manuelnog testiranja pre same automatizacije:

* **Test Cases (Test tekstualni scenariji):** Detaljno napisani koraci za testiranje forme, sa predulovima (Preconditions), koracima (Steps) i očekivanim rezultatima (Expected Result).
* **Bug Reports (Izveštaji o bagovima):** Profesionalno dokumentovani bagovi pronađeni tokom testiranja. Svaki bug report sadrži naslov, ozbiljnost (Severity), prioritet (Priority), korake za reprodukciju (Steps to Reproduce), očekivani nasuprot stvarnom ishodu, kao i okruženje na kojem je testirano.

## 🚀 Tehnologije i Alati
* **Java** (verzija 26) - Programski jezik
* **Selenium WebDriver** (verzija 4.43.0) - Alat za automatizaciju web pretraživača
* **TestNG** (verzija 7.12.0) - Testing frejmvork za izvršavanje testova i organizaciju prioriteta
* **Maven** - Alat za upravljanje zavisnostima (`pom.xml`)
* **IntelliJ IDEA** - Razvojno okruženje (IDE)

---

## 📂 Struktura Projekta

Projekat je organizovan po **Page Object Model (POM)** dizajnerskom šablonu unutar `src/test/java` direktorijuma:

* 📁 **`Base`**
  * `BaseTest.java` — Sadrži setup i teardown metode (`@BeforeMethod`, `@AfterMethod`), drajver inicijalizaciju, implicitna i eksplicitna čekanja (`WebDriverWait`), kao i univerzalne pomoćne metode za lakše skrolovanje i kliktanje preko JavaScript-a (`jsClick`, `scrollToElement`).
* 📁 **`Page`**
  * `HomePage.java` — Lokatori i metode za početnu stranu sajta.
  * `PracticeFormPage.java` — Upravljanje bočnim menijem i odabirom Forms sekcije pomoću dinamičkih listi veb elemenata (`List<WebElement>`) kroz napredne petlje.
  * `RegistrationPage.java` — Centralno mesto za formu. Sadrži sve **lokatore** definisane preko **Page Factory** obrasca (`@FindBy`) za tekstualna polja, kalendar, radio-dugmad, check-boxove i dugme za slanje, kao i pripadajuće **metode** za interakciju sa njima.
* 📁 **`Test`**
  * `TestRegistration.java` — Klasa u kojoj se nalaze svi testovi sa jasno definisanim prioritetima (`priority`) i asertacijama (`Assert`).

---

## 🧪 Obuhvaćeni Test Scenariji

Unutar test klase pokriveno je ukupno 8 scenarija podeljenih u tri grupe:

### 1. Pozitivni Testovi (Happy Path)
* Uspešno slanje forme sa svim ispravno popunjenim poljima (tekst, datumi, selektori, upload slike).
* Uspešno slanje forme isključivo sa obaveznim poljima (Ime, Prezime, Pol, Telefon).

### 2. Validacije i Negativni Testovi
* Provera da se prazna forma ne može poslati (validacija obaveznih polja).
* Validacija neispravnog formata email adrese (npr. bez `@` ili domena).
* Validacija neispravnog formata broja telefona (manje od 10 cifara).

### 3. Automatizovani Stvarni Bagovi (Bug Reporting)
* **Bag sa datumom rođenja:** Test dokazuje da forma pogrešno dopušta slanje i registrovanje korisnika sa datumom rođenja u dalekoj budućnosti.
* **Bag sa 'Close' dugmetom:** Test simulira klik na dugme "Close" na modalnom prozoru nakon uspešne registracije i asertuje da prozor ostaje otvoren (dugme je nefunkcionalno).

---

## 🛠️ Kako pokrenuti testove?

1. Klonirajte repozitorijum:
   `git clone <link_tvog_repozitorijuma>`
2. Otvorite projekat u IntelliJ IDEA.
3. Desni klik na `TestRegistration.java` klasu -> **Run 'TestRegistration'**.
