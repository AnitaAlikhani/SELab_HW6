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

#### ملاحظات
- ارائه گزارش از مراحل پیاده‌سازی الگوهای فوق در فایل README پروژه. لازم است تحقق هر کدام از دو الگوی خواسته شده را به صورت دقیق توضیح دهید.
- بایستی از رویکرد ایجاد آزمون‌رانه (TDD) استفاده کنید.
- حق تغییر کد های نوشته شده را دارید و مانند پیاده سازی های جدید باید با توجه به رویکرد آزمون‌رانه حتما عمل کنید.
- در بخش گراف پروژه برای شما الگوریتم های bfs و دایکسترا پیاده سازی شده است که می توانید به ترتیب برای محاسبه فاصله با استفاده از قطار و با استفاده از اتوبوس استفاده کنید.

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
- یک مورد [Separate Query From Modifier](https://refactoring.guru/separate-query-from-modifier)
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

####  ۷. یکی از انواع بوهای بد، Lazy Class است.
    - این بوی بد در کدام یک از دسته‌بندی‌های پنج‌گانه قرار می‌گیرد؟
    - برای برطرف‌کردن این بو، استفاده از کدام بازآرایی‌ها پیشنهاد می‌شود؟
    - در چه مواقعی باید این بو را نادیده گرفت؟

####  ۸. در وبسایت 29 بوی بد کد نامبرده شده است. سعی کنید 10 بوی بد را در [پروژه تبدیل کننده مدل به سی](https://github.com/bigsheykh/Convert_UML_to_ANSI_C) پیدا کنید و به آن اشاره کنید.

####  ۹. در انتها بگویید پلاگین [formatter](https://code.revelc.net/formatter-maven-plugin/) چه می کند و چرا می تواند کمک کننده باشد و رابطه آن با باز آرایی کد چیست؟






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
