## Lucrare de Laborator Nr.4

### Scop:
__1. Ințelegerea protocolului SMTP de transfer mesaje electronice și metodele de bază;__

__2. Inițializarea proprietăților pentru protocolul SMTP;__

__3. Utilizarea contului de mail real / serverului fals pentru testarea funcțiilor de trimitere mail;__

__4. Concepte de bază ale protocolului SMTP / POP3.__

### Sarcina:
Implementarea unui client de Mail la trimiterea și preluarea mesajelor din contul de e-mail. Clientul ar trebui să utilizeze protocoalele SMTP / POP3.

În această lucrare de laborator a trebuit să creez Mail Client, care va trimite și primi mesaje e-mail. În funcție de sarcina dată, ar trebui să folosesc protocoalele SMTP și POP3.
**SMTP** - serverele de poștă electronică și alți agenți de transfer de e-mail utilizează SMTP pentru a trimite și a primi mesaje de poștă electronică pe portul TCP 25. Aplicațiile poștale client la nivel de utilizator folosesc în mod obișnuit numai SMTP pentru trimiterea mesajelor la un server de mail pentru retransmitere. Pentru aceasta, clienții de poștă electronică trimit în mod obișnuit e-mailurile de ieșire către un server de mail de pe portul 587 sau 465.

**POP3** - pentru recuperarea mesajelor IMAP și POP3 sunt standarde, dar serverele frecvent utilizează propriul sistem de recuperare.Deși majoritatea clienților POP3 au opțiunea de a părăsi poșta pe server după descărcare, în general se conectează, recuperează toate mesajele, le stochează pe client sistem și le șterge de pe server. Pe de altă parte, Internet Message Access Protocol (IMAP), în mod normal, lasă toate mesajele de pe server.
![SMTP](https://user-images.githubusercontent.com/43058513/56412195-13e18100-628c-11e9-8eae-9f50b91b38b3.PNG)

Pentru realizarea trimiterii mesajelor trebuie să deschidem o sesiune și să obținem instanța proprietăților (configurațiilor) de trimitere. Apoi, am creat transportul variabil de tip SMTPTransport pentru conectarea la postul Gmail și trimiterea către receptor a acreditărilor și mesajului cu toate părțile (header, message body, attachments).

![image](https://user-images.githubusercontent.com/43058513/56412552-4475ea80-628d-11e9-8bb3-82e2452db8b7.png)

În funcția MessageInitiliaze am efectuat mesajul MIME (Multi-Purpose Internet Mail Extensions) și inițializat părțile mesajului, unde am adăugat un fișier .txt PR-File.txt și o imagine utm.png. O singură întrebare poate să apară: în cazul în care toate părțile mesajului sunt stocate și trimise? Răspunsul e evident - avem Multipart inițializat in beggining , care este un container care deține mai multe părți din body.

Următoarea funcție în procesul de trimitere a e-mailului este: Attachments (), unde această funcție inițializează o parte abstractă care conține content-ul.

![image](https://user-images.githubusercontent.com/43058513/56413084-f235c900-628e-11e9-8604-590e20d38fdd.png)

Funcția BodyParts() este creată pentru adăugarea content-ului la message.

![image](https://user-images.githubusercontent.com/43058513/56413230-75efb580-628f-11e9-8127-68d7891a9ab7.png)

Ultima funcție este pentru proprietăți, care sunt pentru configurație: host , auth...

![image](https://user-images.githubusercontent.com/43058513/56413674-e77c3380-6290-11e9-9d85-f164c9cca966.png)

Pentru extragerea mesajelor am un alt package, care are clasa RetrieveEmiles, care preia mesajele din Inbox Folder din post. Aici, am  Store provider, care se conectează la store prin POP3. Apoi, am primit Inbox Folder și am citit mesajele primite. Message array este creat pentru a stoca mesajele primite și pentru a le parsa în structura corespunzătoare. In continuare am 2 funcții care sunt pentru parsarea informațiilor din e-mail.

 1. **getTextFromMessage** - getContent() din Message part. 
 
 2. **getTextFromMimeMultipart** - parsarea HTML și obținerea textului din body.
 
 ![image](https://user-images.githubusercontent.com/43058513/56415044-290edd80-6295-11e9-80b5-5af2a468b418.png)

### Output:

![image](https://user-images.githubusercontent.com/43058513/56415377-3e383c00-6296-11e9-985b-a59495e5a305.png)

![image](https://user-images.githubusercontent.com/43058513/56415417-5c05a100-6296-11e9-8a27-a74ce7f24087.png)

![image](https://user-images.githubusercontent.com/43058513/56415469-763f7f00-6296-11e9-9f39-52ac47c824bd.png)

### Concluzii:

În această lucrare de laborator am obținut abilitățile de a efectua Mail Client utilizând protocoalele de trimitere a mesajelor: SMTP și POP3, înțelegînd procesul de trimitere a mesajelor.

În timpul sarcinilor am observat câteva fapte despre SMTP, acest protocol este nesigur, poate fi ușor „hacked", pe de altă parte se poate utiliza sistemul SMTP: permite sincronizarea unui server Smarthost. De exemplu, dacă aveți 200 de calculatoare în birou care utilizează CRM și doriți să vă sincronizați cu serverul SMTP; nu este necesar să configurați SMTP pe fiecare aparat. Trebuie doar să sincronizați un server Smarthost care să gestioneze toate conturile de e-mail.

POP3-ul este excelent pentru a fi folosit: deoarece toate mesajele de poștă electronică se află pe PC-ul dvs., puteți citi poșta dvs. fără să fiți conectat la Internet. Puteți crea mesaje noi pentru a le trimite altora fără a fi conectat la Internet. Dar, există și dezavantaje: toate mesajele sunt stocate pe hard disk alimentând ceea ce este uneori un spațiu foarte valoros. Toate mesajele sunt stocate în sistemul dvs. și confidențialitatea dispare când cineva se așează la aparatul dvs. Chiar dacă cititorul dvs. de e-mail este protejat prin parolă, este adesea posibil ca cineva care știe ce să facă pentru a citi e-mailul dvs. utilizând o altă aplicație pentru a deschide directoarele de e-mail.


