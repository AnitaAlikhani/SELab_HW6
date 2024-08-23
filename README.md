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
الگوی طراحی strategy را برای پیاده‌سازی روش تردد و محاسبه‌ی فاصله‌ی زمانی به کار می‌بریم. این الگوی طراحی به ما اجازه می‌دهد که رفتار و الگوریتم مناسب را بر اساس شرایطی که در آن هستیم، برنامه به صورت داینامیک اجرا کند. در این مورد دو روش تردد با قطار و با اتوبوس داریم که در صورت انتخاب قطار فاصله با الگوریتم bfs و در صورت انتخاب اتوبوس، فاصله با الگوریتم دکسترا محاسبه می‌شود. برای پیاده‌سازی این الگو ابتدا اینترفیس TransportationStrategy را به صورت زیر تعریف می‌کنیم:
![Screenshot from 2024-08-22 13-26-45](https://github.com/user-attachments/assets/71a0f198-f257-4576-8f0a-23fc694b97f7)

و سپس برای هر کدام از روش‌های تردد یک کلاس استراتژی می‌سازیم که این اینترفیس را پیاده‌سازی می‌کنند.
![Screenshot from 2024-08-22 16-10-39](https://github.com/user-attachments/assets/c69ba74b-b698-4e08-948e-a67e5d8b9553)

![Screenshot from 2024-08-22 16-11-26](https://github.com/user-attachments/assets/093bf37e-e43c-442b-a85c-628caf35c96f)

همانطور که می‌بینیم در پیاده‌سازی تابع ‌calculateDistance کلاس BusStrategy از الگوریتم دکسترا و در کلاس TrainStrategy از الگوریتم bfs برای محاسبه‌ی فاصله‌ی زمانی استفاده شده است.

الگوی طراحی state را برای پیاده‌سازی مسیرهای یک‌طرفه و دوطرفه به کار می‌بریم. با کمک این الگو موقعی حالت مسیر تغییر می‌کند، رفتار کلاس مورد نظر نیز عوض می‌شود. برای پیاده‌سازی این الگو یک اینترفیس به اسم RouteState تعریف می‌کنیم:
![Screenshot from 2024-08-22 16-27-07](https://github.com/user-attachments/assets/69a74896-a717-42b6-b697-78cfdb06795a)

سپس برای هر کدام از مسیرهای یک‌طرفه و دوطرفه کلاسی تعریف می‌کنیم که این اینترفیس را پیاده‌سازی کرده است.
![Screenshot from 2024-08-22 16-29-11](https://github.com/user-attachments/assets/059cf26e-49a1-4712-8bf6-a93cbc701030)

![Screenshot from 2024-08-22 16-29-36](https://github.com/user-attachments/assets/98f83e66-504b-42a0-a55b-614201861bc4)

در کلاس BidirectionalState در تابع makeBidirectionalState تمام مسیرها را دو طرفه می‌کنیم و در تابع makeUnidirectional حالت گراف را روی مسیر یک‌طرفه ست می‌کنیم.


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
- دو مورد مختلف غیر از بازآرایی‌های مطرح‌شده در موارد بالا.
    - در [اینجا](https://refactoring.guru/refactoring/techniques) می‌توانید لیستی از تمام بازآرایی‌های موجود را ببینید.
    - بازآرایی‌های خیلی ساده مانند تغییر نام متغیرها، تغییر نوع خصیصه‌های یک کلاس (برای مثال از private به final) یا پاک‌کردن کدهای کامنت‌شده، حساب نیست.
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
   .Bloaters: این دسته شامل اشیاء و کدهایی است که بیش از حد بزرگ و پیچیده شده‌اند. این پیچیدگی باعث می‌شود که درک و نگهداری کد دشوار شود.
    .Object-Orientation Abusers: این دسته شامل کدهایی است که اصول و شیوه‌های شی‌گرایی به درستی رعایت نشده‌اند. این مشکلات اغلب ناشی از درک نادرست از مفاهیم شی‌گرایی است.
    .Change Preventers: این دسته شامل کدهایی است که تغییر در یک بخش از سیستم نیازمند تغییرات در بخش‌های متعدد است. این ویژگی می‌تواند تکامل و بهبود سیستم را مشکل کند.
    .Dispensables: این دسته شامل کدهایی است که در سیستم ضرورت ندارند و می‌توانند حذف شوند بدون آنکه تاثیر منفی بر عملکرد بگذارند.
    .Couplers: این دسته شامل کدهایی است که بیش از حد به یکدیگر وابسته‌اند. وابستگی زیاد میان بخش‌های مختلف سیستم می‌تواند باعث شود که تغییرات کوچک منجر به تغییرات گسترده شوند.


####  ۷. یکی از انواع بوهای بد، Lazy Class است.
   - این بوی بد در کدام یک از دسته‌بندی‌های پنج‌گانه قرار می‌گیرد؟
    Lazy Class در دسته Dispensables (اضافی‌ها) قرار می‌گیرد. این دسته به کدهایی اشاره دارد که ضرورتی در سیستم ندارند و می‌توانند بدون تأثیر منفی بر عملکرد حذف شوند.

   - برای برطرف‌کردن این بو، استفاده از کدام بازآرایی‌ها پیشنهاد می‌شود؟
    Inline Class (کلاس درون‌خط):این بازآرایی شامل قرار دادن محتوای کلاس تنبل داخل کلاسی دیگر (معمولاً کلاس پدر یا مشتری آن) است که مناسب‌تر باشد. اگر یک کلاس عملکرد خیلی کمی دارد و بیش از حد پراکنده است، بهتر است که این کد‌ها درون کلاس مرتبط خود قرار بگیرند.
    Collapse Hierarchy (فروپاشی سلسله‌مراتب):اگر کلاسی تنها وظیفه‌ای که دارد به ارث بردن از یک کلاس دیگر است و به هیچ عملکرد خاصی اضافه نمی‌کند، با استفاده از این بازآرایی می‌توان سلسله‌مراتب کلاس‌ها را فروپاشیده و کلاس پدر را مستقیم جایگزین کرد.   

   - در چه مواقعی باید این بو را نادیده گرفت؟
    ممکن است کلاس در حال حاضر محتوای کمی داشته باشد اما برنامه ای برای افزودن عملکرد های جدید به آن کلاس در آینده داشته باشیم و یا اینکه نگه داشتن کلاس برای پشتیبانی و نگهداری سیستم بهتر باشد و به درک بهتر  و مدیریت کد ها کمک کند.

####  ۸. در وبسایت 29 بوی بد کد نامبرده شده است. سعی کنید 10 بوی بد را در [پروژه تبدیل کننده مدل به سی](https://github.com/bigsheykh/Convert_UML_to_ANSI_C) پیدا کنید و به آن اشاره کنید.

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
