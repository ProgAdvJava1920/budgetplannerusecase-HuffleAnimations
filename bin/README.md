# Programming Advanced - Java
# Opgave algemene oefening
### BudgetPlanner

De bedoeling van deze oefening is het uitwerken van een reële case waarin alle concepten van het opleidingsonderdeel Programming Advanced Java in voorkomen.

Deze concepten zijn:

* Java project management via **Maven**
* Unit testing via **JUnit**
* Beheer van gegevens in een database via: 
	* **JDBC**
	* **JPA**
* Beschikbaar stellen van back-end functionaliteit via WebComponents
	* **Servlets**
	* **RESTful Web Services**
*  Eerste kennismaking met het Spring framework met SpringBoot

De opgave zal in verschillende fasen opgedeeld worden.
Per fase zal er een concept aangehaald worden en toegepast worden in deze oefening.
De bedoeling is dat we op het einde een volledig werkende back-end applicatie hebben waarvan we een api kunnen aanspreken gebruik makend van de verschillende concepten.


## Opgave algemeen
Naam project: BudgetPlanner


De budget planner is een applicatie waarin we uitgaven verrichtingen kunnen gaan registreren en labelen om zo een budget te kunnen opvolgen. 
De verrichtingen moeten handmatig kunnen toegevoegd worden of worden ingelezen uit een rekeninguittreksel via een csv bestand.
Iedere verrichting mag maar één label krijgen. Deze labels moet men kunnen toevoegen, aanpassen en verwijderen. 
Per label moet men verrichtingen binnen een bepaalde tijdspanne kunnen opvragen.


### Opgave1
**Concepten:** Maven / JUnit / Logging 

Lees het rekeninguittreksel (account_payments.csv) in en maak van iedere regel een Account en Payment object.
Print de toString van het Account object vervolgens uit via log4j naar console en in een rolling log bestand output.log. Print ook errors en warnings uit via log4j, gebruik dus geen enkel System.out.print... meer.
De objecten (entities) maak je aan via de reeds meegeven objecten Account en Payment. Deze gaan we later uitbreiden en gebruiken om het database schema aan te maken.


