
# TravelData database with postgresql in Kubernetes.

## Case study of app

The travel agency receives from various contractors (Polish, English, German...) files with travel offers.
Each offer is on a single line of the file and contains, separated by tabs: Counterparty_locations date_exit date_return place cene currency_symbol where:
location - an inscription,
denoting language_country (e.g. en_PL, en_US; as the toString () method returns from the Locale class)
country - name country in the language of the counterparty, date - (departure, return)
date in the format YYY-MM-DD (e.g. 2015-12-31),
place - one of: [sea, lake, mountains] - in the language of the counterparty,
price - number in the format of numbers used in the country of the counterparty,
currency_symbol = PLN, USD, etc.
database offers files (any database engine) is internationalized - presents the client in the JTable table a full set of offers in the language chosen by him and according to selected locale settings.
Select two to three locations to test the application.
The contractor files provide in the project date subdirectory as part of the Project Create TravelData and Database classes.
In the TravelData class, define the method: List<String> travelData.getOffersDescriptionsList (String loc, String dateFormat), which returns a list of subtitles, each of which is a description of one offer from the data directory files, presented according to the rules and in the localization language of loc and with the specified format date (possible formats are defined by the SimpleDateFormat class).
In the Database class, ensure that the database is created and entered into it all offers loaded from files (createDb () method) and open a GUI with a table showing the loaded offers.
The GUI should allow you to select the language and regional settings in which offers are shown.
Any database operations can only be performed in the Database class.
Ensure that the following (only modifiable in green areas)
public class Main {
  public static void main(String[] args) {
    String dirName = System.getProperty("user.home")+"/UTP6dir";
    String resultFileName = "UTP6res.txt";
    Futil.processDir(dirName, resultFileName);
  }
}
worked properly and derived for the following sample data, 
contained in data directory files: 
pl	Japonia	2015-09-01	2015-10-01	jezioro	10000,20	PLN
pl_PL	W³ochy	2015-07-10	2015-07-30	morze	4000,10	PLN
en_GB	United States	2015-07-10	2015-08-30	mountains	5,400.20	USD

following results: 
Japonia 2015-09-01 2015-10-01 jezioro 10 000,2 PLN
W³ochy 2015-07-10 2015-07-30 morze 4 000,1 PLN
Stany Zjednoczone Ameryki 2015-07-10 2015-08-30 góry 5 400,2 USD
Japan 2015-09-01 2015-10-01 lake 10,000.2 PLN
Italy 2015-07-10 2015-07-30 sea 4,000.1 PLN
United States 2015-07-10 2015-08-30 mountains 5,400.2 USD
and then created a database and showed GUI.

## Requirements

 1. postgresql driver installed with maven dependecy - https://mvnrepository.com/artifact/org.postgresql/postgresql 
 2. Docker and Kubernetes installed on your computer

## Preparing Kubernetes Environment
### Windows
 1. Install Docker for Windows [https://hub.docker.com/editions/community/docker-ce-desktop-windows](https://hub.docker.com/editions/community/docker-ce-desktop-windows)
 2. After installation login to your dockerhub account in application.
 3. Go to Docker settings.
 4. In settings window go to Kubernetes tab and Enable Kubernetes.
 5. Wait moment for minikube installation.
 6. After installation you should be able to use kubectl commands from your cmd/powershell or other console agent.
 7. Run scripts/postgres.bat to deploy all necessary configurations and app.
 8. After script is run it will build everything you need to run your postgresql server.
 9. In Last output of script you will get description of service you just created so you should be able to access database using address jdbc:postgresql://127.0.0.1:32706/UTP. Login data is described by postgres-configmap.yaml and by default login: UTP, password: UTP.
