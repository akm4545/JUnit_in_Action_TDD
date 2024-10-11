package printer.good;

//printDocument를 각자의 로직으로 ㅐ정의
//다형성을 활용하면 코드가 단순하고 읽기 쉬워진다
//다형성을 활용하면 수행해야 하는 코드가 런타임에서 결정되므로 복잡한 분기문이 필요 없어지고 소스 코드를 이해하고 테스트하기 쉬워진다
public class DocumentPrinter {
    public void printDocument(Document document){
        document.printDocument();
    }
}
