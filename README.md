# SELab_HW6

## گزارش آزمایش

### فاز اول آزمایش

برای انجام آزمایش، صورت مسئله فرضی زیر را در نظر بگیرید و سعی کنید با بهره گیری از الگوهای طراحی Strategy و State آن را پیاده سازی کنید:

هدف ما، ساخت یک سیستم اطلاع رسانی به شهروندان برای اعلام میزان فاصله و هزینه میان شهر ها است.

ما یک سیستم استانی داریم که شهر ها این ویژگی را دارا هستند.
1. بعضی از آن ها به هم راه دارند و یال مانند به هم وصل هستند.
2. استاندار می تواند این مسیر ها را یکطرفه کند و یا دو طرفه نگه دارد.
3. دو شهری که به هم وصل هستند ۲ روش تردد دارند.

در روش اول قطار سریع السیر را داریم که دقیقا یک واحد زمانی یکسانی را طی کنند که در ابتدا این عدد ۱ می باشد.
در روش دوم اتوبوس ها را داریم که زمان مشخصی دارند که می تواند ۱ واحد زمانی نباشد.


#### اجرای برنامه
 
در اجرای برنامه در ابتدا، گراف شهری دریافت می‌شود.
سپس در یک حلقه تا زمانی که درخواست خروج داده نشده است درخواست های متفاوتی دریافت می شود.
استاندار می تواند ۳ نوع درخواست بدهد:
1. یک درخواست یک طرفه کردن همه مسیر ها است.
2. یک درخواست دو طرفه کردن همه مسیر ها است.
3. واحد زمانی حرکت قطار ها را تغییر بدهد.

شهروندان می توانند ۴ نوع درخواست بدهند:
1. فاصله زمانی از شهر الف به شهر ب با استفاده از قطار چقدر است.
2. فاصله زمانی از شهر الف به شهر ب با استفاده از اتوبوس چقدر است.
3. فاصله زمانی از شهر الف به ب با چه وسیله ای سریع تر است.
4. بعضی از افراد از یکسری شهر ها خوششان نمیاد. به آن ها بگویید که آیا از شهر الف به شهر ب بدون رفتن به شهر مذکور ممکن است یا خیر. 
راهنمایی: برای پیدا کردن جواب متغییر visit شهر مورد نفرت را True قرار دهید.

#### گزارش فاز اول
ابتدا تست‌ها را می‌نویسیم و می‌بینیم که همگی فیل می‌شوند و پیاده‌سازی را برای پاس شدن این تست‌ها انجام می‌دهیم.

![Screenshot from 2024-08-23 19-14-21](https://github.com/user-attachments/assets/459f35e8-f2fc-440e-b7b6-be391b55b2b3)

![Screenshot from 2024-08-23 19-14-56](https://github.com/user-attachments/assets/893a01bd-c285-47e2-a24d-eb51b9a66779)

![Screenshot from 2024-08-23 19-15-15](https://github.com/user-attachments/assets/f4141aa9-0ad2-4812-bd2e-e5c4bba2a899)


الگوی طراحی strategy را برای پیاده‌سازی روش تردد و محاسبه‌ی فاصله‌ی زمانی به کار می‌بریم. این الگوی طراحی به ما اجازه می‌دهد که رفتار و الگوریتم مناسب را بر اساس شرایطی که در آن هستیم، به صورت داینامیک اجرا شود. در این مورد دو روش تردد با قطار و با اتوبوس داریم که در صورت انتخاب قطار فاصله با الگوریتم bfs و در صورت انتخاب اتوبوس، فاصله با الگوریتم دکسترا محاسبه می‌شود. برای پیاده‌سازی این الگو ابتدا اینترفیس TransportationStrategy را به صورت زیر تعریف می‌کنیم:

![Screenshot from 2024-08-22 13-26-45](https://github.com/user-attachments/assets/71a0f198-f257-4576-8f0a-23fc694b97f7)

و سپس برای هر کدام از روش‌های تردد یک کلاس استراتژی می‌سازیم که این اینترفیس را پیاده‌سازی می‌کنند.

![Screenshot from 2024-08-22 16-10-39](https://github.com/user-attachments/assets/c69ba74b-b698-4e08-948e-a67e5d8b9553)

![Screenshot from 2024-08-22 16-11-26](https://github.com/user-attachments/assets/093bf37e-e43c-442b-a85c-628caf35c96f)

همانطور که می‌بینیم در پیاده‌سازی تابع ‌calculateDistance کلاس BusStrategy از الگوریتم دکسترا و در کلاس TrainStrategy از الگوریتم bfs برای محاسبه‌ی فاصله‌ی زمانی استفاده شده است.

در نهایت کلاس TravelContext را تعریف می‌کنیم که بر اساس استراتژی‌ای که برای آن ست می‌شود، فاصله‌ی زمانی را محاسبه می‌کند. از این کلاس هم‌زمان با ست کردن گراف شهری، اینستنس جدید گرفته می‌شود و بر اساس درخواست کاربر استراتژی آن روی یکی از دو مورد TrainStrategy یا BusStrategy ست می‌شود.

![Screenshot from 2024-08-23 18-55-16](https://github.com/user-attachments/assets/ab585bde-f176-47c5-8d50-f7837c952ec5)


الگوی طراحی state را برای پیاده‌سازی مسیرهای یک‌طرفه و دوطرفه به کار می‌بریم. با کمک این الگو موقعی حالت مسیر تغییر می‌کند، رفتار کلاس مورد نظر نیز عوض می‌شود. برای پیاده‌سازی این الگو یک اینترفیس به اسم RouteState تعریف می‌کنیم:

![Screenshot from 2024-08-22 16-27-07](https://github.com/user-attachments/assets/69a74896-a717-42b6-b697-78cfdb06795a)

سپس برای هر کدام از مسیرهای یک‌طرفه و دوطرفه کلاسی تعریف می‌کنیم که این اینترفیس را پیاده‌سازی کرده است.

![Screenshot from 2024-08-23 18-55-58](https://github.com/user-attachments/assets/9a949323-4104-4a11-9e73-1533b17e250c)

![Screenshot from 2024-08-23 18-56-36](https://github.com/user-attachments/assets/8e6092c8-879b-466d-b6c3-8f83e79e3d12)


در کلاس BidirectionalState در تابع makeBidirectionalState کاری نمی‌کنیم چون در حال حاضر در حالت دوطرفه هستیم و برای ترنزیشن به حالت دوطرفه (ماندن در همان حالت) نیازی به انجام کاری نیست و در تابع makeUnidirectional حالت گراف را روی مسیر یک‌طرفه ست می‌کنیم و در واقع directed تمام یال‌ها را true می‌کنیم.

در کلاس UnidirectionalState در تابع makeUnidirectionalState کاری نمی‌کنیم چون در حال حاضر در حالت یک‌طرفه هستیم و برای ترنزیشن به حالت یک‌طرفه (ماندن در همان حالت) نیازی به انجام کاری نیست و در تابع makeBidirectional حالت گراف را روی مسیر دوطرفه ست می‌کنیم و در واقع directed تمام یال‌ها را false می‌کنیم.

در کلاس Graph دو تابع زیر را برای تغییر حالت مسیر تعریف می‌کنیم که در صورتی که کاربر درخواست تغییر مسیر را دهد، بر اساس حالت مسیر یکی از این توابع روی گراف شهری صدا زده می‌شوند.

![Screenshot from 2024-08-23 19-01-12](https://github.com/user-attachments/assets/1db7d87a-4ec9-427a-ac76-24d596fecb64)

برای اچرای برنامه هم در تابع main ابتدا گراف شهری و TravelContext را initialize می‌کنیم و از کاربر اسامی شهرها را می‌گیریم و راس‌ها را می‌سازیم و به گراف اضافه می‌کنیم.

![Screenshot from 2024-08-23 19-04-08](https://github.com/user-attachments/assets/9d8ba0d6-213f-4c06-9abf-8b764035a59e)

سپس مسیرها را از کاربر می‌گیریم و یال‌ها را می‌سازیم.

![Screenshot from 2024-08-23 19-05-57](https://github.com/user-attachments/assets/d6b0624b-2108-469b-840b-5542c96c33a9)

در نهایت هم درخواست‌های کاربر را تا وقتی خارج نشود را می‌گیریم و پردازش می‌کنیم.

![Screenshot from 2024-08-23 19-08-12](https://github.com/user-attachments/assets/49a17aad-0d33-4f23-81ff-1764659050a3)

![Screenshot from 2024-08-23 19-08-34](https://github.com/user-attachments/assets/877399e8-ae95-4f68-bcea-c481cd0f26de)


### فاز دوم آزمایش
در این فاز آزمایش قرار است هفت مورد بازآرایی بر روی بخش MiniJava این پروژه انجام دهید:
- دو مورد اعمال الگوی Facade  
الگوی Facade یک رابط ساده و یکپارچه برای دسترسی به یک زیرسیستم پیچیده ارائه می‌دهد و استفاده از آن را برای کاربران آسان‌تر می‌کند. این الگو با پنهان کردن جزئیات پیاده‌سازی و پیچیدگی‌های داخلی، تعاملات با کلاس‌های مختلف را ساده‌تر می‌کند.  
در این بازآرایی، با استفاده از الگوی Facade دو کلاس جدید ایجاد کردیم که رابطی ساده و یکپارچه برای تعامل با زیرسیستم‌های پیچیده‌ی Parser و CodeGenerator فراهم می‌کنند.
```
package MiniJava.parser;

import MiniJava.errorHandler.ErrorHandler;

public class ParserFacade {
    private Parser parser;
    private ErrorHandler errorHandler;

    public ParserFacade() {
        this.errorHandler = new ErrorHandler();
        ParseTable parseTable = new ParseTable();
        this.parser = new Parser(parseTable, errorHandler);
    }

    public void parseInput(String input) {
        parser.parse(input);
    }

    public void addParseRule(NonTerminal nonTerminal, Rule rule) {
        parser.getParseTable().addRule(nonTerminal, rule);
    }

}
```
```
package MiniJava.codeGenerator;

import java.util.ArrayList;

public class CodeGenerationFacade {
    private CodeGenerator codeGenerator;

    public CodeGenerationFacade() {
        this.codeGenerator = new CodeGenerator();
    }

    public void addVariable(String name, varType type) {
        Address address = new Address(codeGenerator.getMemory().getNextAddress(), type);
        codeGenerator.getMemory().addVariable(name, address);
    }

    public void generateOperation(String operation, String sourceName, String destinationName) {
        Address source = codeGenerator.getMemory().getAddress(sourceName);
        Address destination = codeGenerator.getMemory().getAddress(destinationName);
        if (source != null && destination != null) {
            Operation op = new Operation(operation, source, destination);
            codeGenerator.addOperation(op);
        } else {
            System.err.println("Invalid source or destination for operation.");
        }
    }

    public ArrayList<Operation> getOperations() {
        return codeGenerator.getOperations();
    }

    public void executeCode() {
        codeGenerator.generateCode();
    }

}
```
- یک مورد [State/Strategy](https://refactoring.guru/replace-type-code-with-state-strategy) یا [استفاده از Polymorphism به جای شرط](https://refactoring.guru/replace-conditional-with-polymorphism) 
- یک مورد Separate Query From Modifier  
بازآرایی "Separate Query from Modifier" به معنای جدا کردن وظایف یک متد است که همزمان داده‌ای را برمی‌گرداند (Query) و وضعیت را تغییر می‌دهد (Modifier)، به گونه‌ای که این دو وظیفه در متدهای جداگانه انجام شوند.
در اینجا، متد `getTemp` در کلاس `Memory` که همزمان مقدار `lastTempIndex` را تغییر می‌داد و آن را برمی‌گرداند، بازآرایی شد. این متد به دو متد جداگانه تقسیم شد: یکی برای تغییر مقدار (`incrementTempIndex`) و دیگری برای برگرداندن مقدار (`getTempValue`).
```
public int getTemp() {
    lastTempIndex += tempSize;
    return lastTempIndex - tempSize;
}
```
```
public void incrementTempIndex() {
    lastTempIndex += tempSize;
}

public int getTempValue() {
    return lastTempIndex - tempSize;
}

public int getTemp() {
    incrementTempIndex();
    return getTempValue();
}
```

- یک مورد [Self Encapsulated Field](https://refactoring.guru/self-encapsulate-field).
  برای اعمال این بازآرایی Address.java را انتخاب کردیم.
  لازمه بازآرایی Self encapsulated field تبدیل فیلد های پابلیک به پرایوت و ایجاد getter و setter برای تمام فیلد های پرایوت است که به منظور امنیت بیشتر انجام می‌شود.
  پس از این مراحل نیز باید usage های این کلاس را پیدا کرده و آن ها را اصلاح کنیم.
  تغییر فیلد ها از public به private و تغییر constructor ها:
  ![private fields and new constructors after refactoring Address java](https://github.com/user-attachments/assets/4df595b2-67d8-4d20-a9ca-f82a5e3c6fba)

  ایجاد getter ها و setter ها:
  ![getters and setters in Address java](https://github.com/user-attachments/assets/8a2c0225-3caa-4481-b039-9cb5f217d470)

  تغییر usage های این قسمت در کلاس های دیگر:
![before refactor](https://github.com/user-attachments/assets/1f6551fe-45cb-4c9e-8554-01c2c8fa0236)
![usages after refactor](https://github.com/user-attachments/assets/08cad23e-321d-4e69-b9e3-7d49d92e9877)

  


- دو مورد مختلف غیر از بازآرایی‌های مطرح‌شده در موارد بالا.
    - در [اینجا](https://refactoring.guru/refactoring/techniques) می‌توانید لیستی از تمام بازآرایی‌های موجود را ببینید.
    - بازآرایی‌های خیلی ساده مانند تغییر نام متغیرها، تغییر نوع خصیصه‌های یک کلاس (برای مثال از private به final) یا پاک‌کردن کدهای کامنت‌شده، حساب نیست.
    مورد بعدی بازآرایی Extract method است. برای این بازآرایی باید بخش‌های طولانی‌تر یا پیچیده‌تر کد را شناسایی کرده و آن‌ها را به متدهای مستقل‌تر و کوچکتر تبدیل کنیم.
      برای این کار متود pintCodeBlock در کلاس Memory.java را انتخاب می‌کنیم، بازآرایی انجام شده باعث می‌شود کد تمیزتر شود و هر متد وظیفه خاصی داشته باشد، که به خوانایی و نگه‌داشت بهتر کمک می‌کند.
      متود قبل از بازآرایی:
      ![pint code block before refactor](https://github.com/user-attachments/assets/a61df6d4-fef1-4904-92c4-8f78553c1fab)


      متود پس از بازآرایی:
      
![pint code block after refactor](https://github.com/user-attachments/assets/752b7826-d2e9-4f27-8389-9453f6e39ddc)


 - بعد از بازآرایی، پلاگین [formatter](https://code.revelc.net/formatter-maven-plugin/) را به فایل maven اضافه کنید.

به ازای هر بازآرایی، یک commit کنید و نام آن بازآرایی را هم بیاورید.

در این گزارش هر کدام از بازآرایی‌های انجام شده را در حد دو خط توضیح دهید.

## پرسش‌ها


####  ۱. در کتاب GoF سه دسته الگوی طراحی معرفی شده است. آن‌ها را نام ببرید و در مورد هر دسته در حد دو خط توضیح دهید.
   
۱. الگوهای Creational: این دسته از الگوها روی فرآیند ساخت شی و منعطف سازی نحوه‌ی نمونه‌گیری از اشیا (instantiation) تمرکز دارند. این الگوها فرآیند ساخت شی را با کپسوله کردن منطق ساخت شی و مستقل کردن سیستم از نحوه‌ی انجام این فرآیند و نمایش اشیا، انجام می‌دهند.
        
۲. الگوهای Structural: این دسته از الگوها به نحوه‌ی ترکیب کلاس‌ها و اشیا برای تشکیل ساختارهای بزرگتر مرتبط هستند. هدف این الگوها این است که وقتی بخشی از یک سیستم تغییر می‌کند، نیازی به تغییر دادن کل ساختار سیستم نباشد. این الگوها روی اصلاح ساختار با یافتن راه‌های ساده برای تحقق روابط بین موجودیت‌ها تمرکز می‌کنند.

۳. الگوهای Behavioral: این دسته از الگوها به نحوه‌ی تعامل و مسئولیت اشیا مرتبط هستند و روی اصلاح یا ساده‌سازی ارتباط بین اشیای متفاوت در یک سیستم تمرکز دارند. این الگوها با کپسوله کردن اطلاعات در مورد اینکه چه کلاس‌هایی از یکدیگر استفاده می‌کنند، به تعریف الگوی کلی ارتباط کمک می‌کنند.

####  ۲. الگوهای استفاده شده در فاز اول آزمایش جزو کدام دسته هستند؟  
دسته‌ی سوم، الگوهای رفتاری (Behavioral)

####  ۳. با توجه به این که در فاز اول در هر زمان درخواست دقیقا در یکی از دو حالت یک طرفه و یا دو طرفه هستیم کدام الگوی طراحی را برای پاسخ دادن به سوال کاربران مناسب می‌دانید؟ ضمن بیان علت انتخاب خود، نحوه تحقق الگو را به طور کامل توضیح دهید.
الگوی طراحی state - مسیرها می‌توانند به طور کامل یا یک‌طرفه باشند یا دوطرفه و بر اساس اینکه در کدام یکی از این دو حالت قرار گرفته باشند، کاربر پاسخ متفاوتی به سوال خود می‌گیرد چون رفتار کلاس مورد نظر تغییر می‌کند. در فاز اول هم برای تحقق این الگو ابتدا یک اینترفیس RouteState را تعریف می‌کنیم که شامل دو تابع برای یک‌طرفه یا دوطرفه کردن مسیر است. سپس برای هر کدام از حالات کلاسی را تعریف می‌کنیم که این اینترفیس را  پیاده‌سازی کرده است. تابع makeBidirectional در کلاس BidirectionalState کاری انجام نمی‌دهد چون از تغییر حالت دوطرفه به حالت دوطرفه نیازی به انجام کاری نیست و در همان حالت قبلی مانده‌ایم ولی پیاده‌سازی این تابع در کلاس UnidirectionalState که یعنی در حالت مسیر یک‌طرفه هستیم، حالت گراف را روی دوطرفه ست می‌کند و تمام یال‌ها را یک‌طرفه می‌کند. تابع makeUnidirectional در کلاس UnidirectionalState نیز کاری نمی‌کند چون در همان حالت مانده‌ایم ولی پیاده‌سازی این تابع در کلاس BidirectioanlState به اینگونه است که حالت گراف را روی دوطرفه ست می‌کند چون از حالت یک‌طرفه می‌خواهیم به حالت دوطرفه ترنزیشن داشته باشیم.

####  ۴. تحقق و یا عدم تحقق هر کدام از اصول SOLID را در خصوص الگوی طراحی Singleton بیان کنید (هرکدام حداکثر در سه خط).  

اصل (SRP): الگوی Singleton اصل SRP را نقض می‌کند زیرا دو مسئولیت را بر عهده دارد: مدیریت ایجاد شیء و انجام وظیفه اصلی کلاس. در حالی که یک کلاس باید تنها یک مسئولیت داشته باشد، Singleton این دو را در یک کلاس ترکیب می‌کند.

اصل (OCP): Singleton اصل OCP را نقض می‌کند زیرا کلاس به خودی خود کنترل ایجاد نمونه (Instance) را در دست دارد، که این باعث وابستگی شدید به پیاده‌سازی خاص آن می‌شود. این امر گسترش یا تغییر کلاس را بدون اعمال تغییرات گسترده در کل پروژه دشوار می‌کند.

اصل (LSP): به دلیل نقض OCP و مشکلات ارث‌بری، LSP نیز ممکن است به خطر بیفتد، زیرا کلاس‌های Singleton اغلب ارث‌بری را محدود می‌کنند یا اگر اجازه دهند، الگوی Singleton دیگر معتبر نخواهد بود.

اصل (ISP): اگرچه این اصل به طور مستقیم توسط Singleton نقض نمی‌شود، اما مشکلات ناشی از نقض اصول SRP و OCP می‌تواند به صورت غیرمستقیم بر ISP تأثیر بگذارد، به ویژه اگر رابط‌های کلاس بیش از حد پیچیده شوند.

اصل (DIP): Singleton اصل DIP را نقض می‌کند زیرا باعث وابستگی مستقیم به یک کلاس خاص می‌شود به جای اینکه از انتزاعات (Interfaces) استفاده کند، که این منجر به کدی می‌شود که به سختی قابل نگهداری و گسترش است.

 منبع: https://medium.com/divar-mobile-engineering/use-singleton-pattern-or-not-282b197a2e9f

####  ۵. هر یک از مفاهیم زیر را در حد یک خط توضیح دهید.

   کد تمیز: کدی که خوانا، ساده و بدون تکرار بوده و به راحتی توسط سایر برنامه‌نویسان درک و نگهداری می‌شود.  
   بدهی فنی: هزینه‌های اضافی و مشکلاتی که به دلیل انتخاب راه‌حل‌های سریع و غیر ایده‌آل در کدنویسی به مرور زمان ایجاد می‌شوند.  
   بوی بد: نشانه‌ها و الگوهایی در کد که نشان می‌دهند طراحی یا پیاده‌سازی نیاز به بهبود و بازسازی دارد.  

####  ۶. طبق دسته‌بندی وب‌سایت [refactoring.guru](https://refactoring.guru/refactoring/smells)، بوهای بد کد به پنج دسته تقسیم می‌شوند. در مورد هر کدام از این پنج دسته توضیح مختصری دهید.
 - Bloaters: این دسته شامل اشیاء و کدهایی است که بیش از حد بزرگ و پیچیده شده‌اند. این پیچیدگی باعث می‌شود که درک و نگهداری کد دشوار شود.  
 - Object-Orientation Abusers: این دسته شامل کدهایی است که اصول و شیوه‌های شی‌گرایی به درستی رعایت نشده‌اند. این مشکلات اغلب ناشی از درک نادرست از مفاهیم شی‌گرایی است. 
 - Change Preventers: این دسته شامل کدهایی است که تغییر در یک بخش از سیستم نیازمند تغییرات در بخش‌های متعدد است. این ویژگی می‌تواند تکامل و بهبود سیستم را مشکل کند.
 - Dispensables: این دسته شامل کدهایی است که در سیستم ضرورت ندارند و می‌توانند حذف شوند بدون آنکه تاثیر منفی بر عملکرد بگذارند.
 - Couplers: این دسته شامل کدهایی است که بیش از حد به یکدیگر وابسته‌اند. وابستگی زیاد میان بخش‌های مختلف سیستم می‌تواند باعث شود که تغییرات کوچک منجر به تغییرات گسترده شوند.


####  ۷. یکی از انواع بوهای بد، Lazy Class است.
   - این بوی بد در کدام یک از دسته‌بندی‌های پنج‌گانه قرار می‌گیرد؟

   Lazy Class در دسته Dispensables (اضافی‌ها) قرار می‌گیرد. این دسته به کدهایی اشاره دارد که ضرورتی در سیستم ندارند و می‌توانند بدون تأثیر منفی بر عملکرد حذف شوند.

   - برای برطرف‌کردن این بو، استفاده از کدام بازآرایی‌ها پیشنهاد می‌شود؟

   Inline Class (کلاس درون‌خط):این بازآرایی شامل قرار دادن محتوای کلاس تنبل داخل کلاسی دیگر (معمولاً کلاس پدر یا مشتری آن) است که مناسب‌تر باشد. اگر یک کلاس عملکرد خیلی کمی دارد و بیش از حد پراکنده است، بهتر است که این کد‌ها درون کلاس مرتبط خود قرار بگیرند.
    Collapse Hierarchy (فروپاشی سلسله‌مراتب):اگر کلاسی تنها وظیفه‌ای که دارد به ارث بردن از یک کلاس دیگر است و به هیچ عملکرد خاصی اضافه نمی‌کند، با استفاده از این بازآرایی می‌توان سلسله‌مراتب کلاس‌ها را فروپاشیده و کلاس پدر را مستقیم جایگزین کرد.   

   - در چه مواقعی باید این بو را نادیده گرفت؟

   ممکن است کلاس در حال حاضر محتوای کمی داشته باشد اما برنامه ای برای افزودن عملکرد های جدید به آن کلاس در آینده داشته باشیم و یا اینکه نگه داشتن کلاس برای پشتیبانی و نگهداری سیستم بهتر باشد و به درک بهتر  و مدیریت کد ها کمک کند.

####  ۸. در وبسایت 29 بوی بد کد نامبرده شده است. سعی کنید 10 بوی بد را در [پروژه تبدیل کننده مدل به سی](https://github.com/bigsheykh/Convert_UML_to_ANSI_C) پیدا کنید و به آن اشاره کنید.

 - Lazy class: کلاس CommandExecutor متود های کمی دارد و می‌تواند با کلاس دیگری ادغام شده یا متود های بیشتری را شامل شود.
 - Long method: کلاس main شامل متود های طولانی است که بهتر است کوتاه شوند یا به کلاس دیگر انتقال پیدا کنند.
 - Large class: کلاس main متود های زیادی را هندل می‌کند و این باعث طولانی شدن این کلاس می‌شود.
 - Primitive Obsession: به جای استفاده از کلاس های کوچک و تعریف شده از داده های اولیه استفاده شده است. مانند استفاده از string برای دستورات و فرمت های تاریخ.
 - Data Clumps: در کلاس Main، منطق تکراری برای مدیریت وضعیت دیاگرام و عملیات گرافیک نشان‌دهنده Data Clumps است.
 - Switch Statements: در کلاس Main، استفاده از عبارت switch برای مدیریت آرگومان‌های command line ، Switch Statements است
 - Dead Code:  Dead Code زمانی رخ می‌دهد که قطعاتی از کد در برنامه وجود دارند که هرگز اجرا نمی‌شوند یا استفاده نمی‌شوند.در کلاس Main، کد کامنت شده در متد generateInfoForXML ، Dead Code است
 - Speculative Generality: بوی بد Speculative Generality زمانی رخ می‌دهد که کد به صورت بیش از حد عمومی و انتزاعی نوشته می‌شود، در کلاس Main، متد loopOnGUI نمونه‌ای از Speculative Generality است، جایی که منطق موجود بسیار پیچیده‌تر از نیازهای فعلی است
 - Feature Envy: بوی بد Feature Envy زمانی رخ می‌دهد که یک متد در یک کلاس بیشتر به داده‌های کلاس دیگری علاقه‌مند است تا به داده‌های کلاس خود. در کلاس Main، متد loopOnGUI نمونه‌ای از Feature Envy است، جایی که به طور زیادی به داده‌های کلاس GUIDiagram وابسته است
 - Inappropriate Intimacy: این بوی بد نشان دهنده وابستگی‌های زیاد و غیرضروری بین کلاس‌هاست. در کلاس main نیز وابستگی زیادی به GUIDiagram وجود دارد.



####  ۹. در انتها بگویید پلاگین [formatter](https://code.revelc.net/formatter-maven-plugin/) چه می کند و چرا می تواند کمک کننده باشد و رابطه آن با باز آرایی کد چیست؟ 
   پلاگین formatter یکی از ابزارهای مهم در توسعه نرم‌افزار است که به بازآرایی کد کمک می‌کند. هدف اصلی استفاده از formatter، اطمینان از یکپارچگی و هماهنگی در سبک کدنویسی و بهبود خوانایی کد است. این ابزار می‌تواند خودکار‌سازی فرآیندهایی که معمولاً دستی و زمان‌بر هستند، را فراهم کند و باعث کاهش خطا و سریع تر شدن بازآرایی کد شود. بازآرایی کد فرآیند بهبود ساختاری کد بدون تغییر در رفتار خارجی آن است. یکی از مهم‌ترین چالش‌های این فرآیند، حفظ خوانایی و هماهنگی کدها پس از اعمال تغییرات است. پلاگین‌های formatter با افزایش سرعت فرآیند بازآرایی، کاهش تداخل کد ها و بهبود ساختار کد در این زمینه کمک می‌کنند.




# MiniJava
Mini-Java is a subset of Java. MiniJava compiler implement a compiler for the Mini-java
programming language.

# Rules of MiniJava
```
Goal --> Source EOF
Source --> ClassDeclarations MainClass
MainClass --> class Identifier { public static void main() { VarDeclarations Statements}}
ClassDeclarations --> ClassDeclaration ClassDeclarations | lambda
ClassDeclaration --> class Identifier Extension { FieldDeclarations MethodDeclarations }
Extension --> extends Identifier | lambda
FieldDeclarations --> FieldDeclaration FieldDeclarations | lambda
FieldDeclaration --> static Type Identifier ;
VarDeclarations --> VarDeclaration VarDeclarations | lambda
VarDeclaration --> Type Identifier ;
MethodDeclarations --> MethodDeclaration MethodDeclarations | lambda
MethodDeclaration --> public static Type Identifier ( Parameters ) { VarDeclarations Statements return GenExpression ; }
Parameters --> Type Identifier Parameter | lambda
Parameter --> , Type Identifier Parameter | lambda
Type --> boolean | int
Statements --> Statements Statement | lambda
Statement --> { Statements } | if ( GenExpression ) Statement else Statement | while ( GenExpression ) Statement | System.out.println ( GenExpression ) ; | Identifier = GenExpression ;
GenExpression --> Expression | RelExpression
Expression --> Expression + Term | Expression - Term | Term
Term --> Term * Factor | Factor
Factor --> ( Expression ) | Identifier | Identifier . Identifier | Identifier . Identifier ( Arguments ) | true | false | Integer
RelExpression --> RelExpression && RelTerm | RelTerm
RelTerm --> Expression == Expression | Expression < Expression
Arguments --> GenExpression Argument | lambda
Argument --> , GenExpression Argument | lambda
Identifier --> <IDENTIFIER_LITERAL>
Integer --> <INTEGER_LITERAL>
```
