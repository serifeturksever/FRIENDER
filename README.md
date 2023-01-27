# FRIENDER

## 1.Uygulama Özellikleri:

Uygulamamız 6 adet kategori içermektedir.Kullanıcı bu kategorilerden birini seçip seçtiği kategori içerisinde oda oluşturabilir ve yeni insanlarla tanışıp sohbet edebilir. Örnek üzerinden ilerleyecek olursak kullanıcı arkadaşlarıyla voleybol maçı yapacak.Ancak takımlar kurulurken 1 kişi eksik olduklarını görüyorlar.Spor kategorisinde bir oda oluşturup voleybol maçı için birini aradıklarını belirtiyorlar. Bu ekibe katılmak isteyen kişiler ise bu sohbet odasına girip sohbet ederek anlaşmaya varıyorlar. Sadece sohbet etmek isteyen bir kişi ise chat kategorisinde bir oda açabilir ve tanıdığı/tanımadığı insanlarla sohbet edebilir.
  Kullanıcılar uygulamaya girmeden önce kaydolmak ve giriş yapmak zorundadırlar. Bu input alanlarında hatalı giriş yapılması veya bir giriş yapılmaması durumunda validation mesajları kullanılmıştır.Kullanıcı kayıt olurken veya giriş yaparken hatalı girişler yaparsa toast mesajları ile kullanıcı uyarılmaktadır (yanlış email formatı,6 karakterden az şifre,giriş yaparken yanlış kullanıcı adı veya şifre gibi durumlara ek olarak çok fazla yanlış giriş yapılırsa sistem o kullanıcı için bir süre kilitlenmektedir).Giriş yapan kullanıcıyı toast mesajı (user logged in successfully) ile kategorilerin bulunduğu bir sayfa karşılamaktadır. Kullanıcı çıkmak istediğinde bu sayfanın altında bulunan (down scroll yaparak görebilirsiniz) logout butonu ile çıkış yapabilir. Uygulamamızda firebase (nosql) database kullanılmaktadır. Kullanıcılar kaydolunca veritabanına yazılır ve o şekilde login işlemleri kontrol edilir. İstenilen kategoride minimum 4 karakter kullanarak oda oluşturulabilir. Odanın oluşturulma tarihi ve odayı oluşturan kişi ekranda gösterilmektedir. Kullanıcı odayı silmek istediğinde "X" butonuna basarak odayı silebilir. 
  Mesaj sayfasında kişinin kendisinin attığı mesajlar farklı bir renkte (mor) ve sağa dayalı , diğer insanların attığı mesajlar ise farklı renkte (açık mavi) ve sola dayalı şekilde görülmektedir. Mesajların oluşturulma tarihi ekranda gösterilmektedir.
  Uygulamamız daha önce tanışmayan insanları farklı aktiviteleri yapmak adına bir araya getirebiliyor. Sıkıldığınızda, yemek yemek, oyun oynamak, ders çalışmak, spor yapmak için birilerine ihtiyaç duyduğunuzda, öylesine bir akşamüstü yürüyüşüne çıkmak istediğinizde, bir etkinlik için yanınıza birilerini aradığınızda veya sadece sohbet edip kafa dağıtmak istediğinizde uygulamamızı kullanarak güzel vakit geçirebilirsiniz.   
Uygulamamızın her sayfasında landscape özelliği bulunmaktadır. Her sayfa hem yan (landscape mode) hem düz (portrait mode) şekilde kullanılabilmektedir.

## 2.Kullanıcı Kılavuzu:

Kullanıcı sistemde kayıtlı değilse karşısına çıkan login ekranının alt kısmındaki "Register here" kısmından kayıt olması gerekmektedir. Burada email ve password için validation işlemlerimiz bulunmaktadır. Örneğin kullanıcı email inputuna "a" giremez. Girilen input email formatına uygun olmalıdır. Password ise en az 6 karakterden oluşmalıdır. Kullanıcı gerekli koşullara uyarak "REGISTER" butonuna basınca kayıt olur ve giriş yapması için login sayfasına yönlendirilir. Burada kullanıcı adı ve şifresini gerekli input alanlarına girerek "LOGIN" butonuna basar ve uygulamaya giriş yapar.Bu kısımda 6 kategori ve 1 logout butonu kullanıcıyı karşılar. Kullanıcı uygulamadan çıkmak isterse logout butonuna basar ve uygulamadan çıkış yaparak login sayfasına yönlendirilir. Eğer kullanıcı bu 6 kategoriden birisini seçerse oda eklemek için bir buton bulunduğunu görecektir. Bu butona tıkladığında kullanıcıyı input alanı ve buton karşılar. Kullanıcı input alanına odasına vermek istediği ismi girer. Eğer isim 3 veya daha az karakterden oluşuyorsa sistem bunu kabul etmez ve input alanı altında bir uyarı mesajı görür. Kullanıcı 4 veya daha fazla karakterden oluşan bir oda ismi yaratırsa oda oluşur ve bu oda ismine tıklandığında kullanıcı sohbet odasına yönlendirilmiş olur. Üst kısımda kategori ve oda ismi "-" ile ayrılmış şekilde gösterilmektedir. İstenilen mesaj input alanına yazılarak butona basılır ve mesaj ekranda görünür. Böylece sohbet başlamış olur ve oda ismi veritabanına kayıt edilir.

## 3.Uygulama Ekran Görüntüleri:

<img width="421" alt="Screen Shot 2022-01-13 at 17 12 21" src="https://user-images.githubusercontent.com/62253012/215024870-2481fee4-8b0a-41fd-ae0a-757d5d0942aa.png">
<img width="427" alt="Screen Shot 2022-01-13 at 17 13 19" src="https://user-images.githubusercontent.com/62253012/215024879-338055fe-f085-4eca-912b-f118529db467.png">
<img width="422" alt="Screen Shot 2022-01-13 at 17 13 29" src="https://user-images.githubusercontent.com/62253012/215024883-5e193aaf-345c-496b-9925-09bf865cffa7.png">
<img width="453" alt="Screen Shot 2022-01-13 at 17 15 47" src="https://user-images.githubusercontent.com/62253012/215024886-36341ee2-a6d8-41ac-9c34-952fcf60d667.png">
<img width="427" alt="Screen Shot 2022-01-13 at 17 16 02" src="https://user-images.githubusercontent.com/62253012/215024891-51bc81cf-8a40-416a-80e5-0ade121855b2.png">
<img width="431" alt="Screen Shot 2022-01-13 at 17 16 17" src="https://user-images.githubusercontent.com/62253012/215024899-e764c7e5-ec3f-44ae-b725-4de1a44a5c0a.png">
<img width="439" alt="Screen Shot 2022-01-13 at 17 16 31" src="https://user-images.githubusercontent.com/62253012/215024906-7878fd83-6b75-4a5f-83e9-a31c08534765.png">
<img width="437" alt="Screen Shot 2022-01-13 at 17 16 59" src="https://user-images.githubusercontent.com/62253012/215024910-b3d7ab99-bf8c-4fb6-b1d7-8302bd7baa71.png">
<img width="436" alt="Screen Shot 2022-01-13 at 17 17 59" src="https://user-images.githubusercontent.com/62253012/215024915-8949c1c2-4dc1-4fbd-bf3d-5778c116ae80.png">
<img width="430" alt="Screen Shot 2022-01-13 at 17 18 20" src="https://user-images.githubusercontent.com/62253012/215024920-35042754-6a93-4560-9424-8ca74b12f725.png">
<img width="436" alt="Screen Shot 2022-01-13 at 17 18 43" src="https://user-images.githubusercontent.com/62253012/215024921-e1040c6b-5201-4931-a3c2-28e71eeb49f4.png">
<img width="923" alt="Screen Shot 2022-01-13 at 17 23 04" src="https://user-images.githubusercontent.com/62253012/215024925-d4e10eeb-f439-4bbf-abad-c29164ede687.png">
<img width="940" alt="Screen Shot 2022-01-13 at 17 23 11" src="https://user-images.githubusercontent.com/62253012/215024926-73bdbe05-647f-4ce5-ab34-edabf345798a.png">
<img width="380" alt="Screen Shot 2022-01-15 at 09 49 23" src="https://user-images.githubusercontent.com/62253012/215024932-a5823838-a440-4f21-a3f6-ff3589b096b5.png">
<img width="440" alt="Screen Shot 2022-01-15 at 09 50 03" src="https://user-images.githubusercontent.com/62253012/215024935-b1b8117c-0aac-4102-bfe4-6ffb89161158.png">
<img width="432" alt="Screen Shot 2022-01-15 at 09 50 39" src="https://user-images.githubusercontent.com/62253012/215024938-5f2ece21-845b-4c86-b562-5ccf21adf876.png">
<img width="462" alt="Screen Shot 2022-01-15 at 09 50 56" src="https://user-images.githubusercontent.com/62253012/215024939-fada333f-bf22-4b95-894f-30ae25f73534.png">
<img width="421" alt="Screen Shot 2022-01-15 at 09 52 10" src="https://user-images.githubusercontent.com/62253012/215024943-8a51e49b-daa4-43e1-a485-04e9c70e5f40.png">
<img width="438" alt="Screen Shot 2022-01-15 at 09 52 32" src="https://user-images.githubusercontent.com/62253012/215024947-d66dff6f-5df0-4ecc-b4a8-7d920a869292.png">



