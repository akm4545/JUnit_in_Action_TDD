package printer.bad;

//아래 예제는 테스트와 유지 보수 모두 어려워 보인다.
//만약 새 documentType이 추가된다면 필연적으로 새 case문이 추가될 것이다.
//게다가 애플리케이션에서 이런 상황이 발생할 때마다 분기 처리를 하는 모든 곳에서 매번 로직을 추가해야 한다
public class DocumentPrinter {
    private Document document;

    public DocumentPrinter(Document document){
        this.document = document;
    }

    public void printDocument() {
        switch (document.getDocumentType()){
            case WORD_DOCUMENT:
                printWORDDocument();
                break;
            case PDF_DOCUMENT:
                printPDFDocument();
                break;
            case TEXT_DOCUMENT:
                printTextDocument();
                break;
            default:
                printBinaryDocument();
                break;
        }
    }

    private void printBinaryDocument() {

    }

    private void printTextDocument() {

    }

    private void printPDFDocument() {

    }

    private void printWORDDocument(){

    }
}
