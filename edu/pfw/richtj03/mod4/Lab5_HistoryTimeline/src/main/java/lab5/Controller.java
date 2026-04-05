package lab5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.ListIterator;

public class Controller {

    @FXML
    private ImageView historyIV;

    @FXML
    private Label titleLbl;
    @FXML
    private Label historyLbl;
    @FXML
    private Label timelineLbl;

    private LinkedList<HistorySegment> history;
    private ListIterator<HistorySegment> iterator;

    @FXML
    void nextHistory(ActionEvent event) {
        //TODO Show the next history segment
        if(iterator.hasNext()) {
            HistorySegment next = iterator.next();
            showHistory(next);
        }

    }

    @FXML
    void previousHistory(ActionEvent event) {
        //TODO Show the previous history segment
        if(iterator.hasPrevious()) {
            HistorySegment prev = iterator.previous();
            showHistory(prev);
        }

    }

    @FXML
    void initialize() {
        history = new LinkedList<>();
        history.add(new HistorySegment(
                "Pre Computing Era",
                "Early computing concepts emerged with mechanical devices like the abacus (2400 BCE) and later innovations such as Pascal’s calculator (1642) and Leibniz’s stepped reckoner (1673). These early tools were designed to assist with arithmetic but lacked programmability.",
                "https://mechanicalcomputing.wordpress.com/wp-content/uploads/2015/12/pascaline31.jpg?w=700"
        ));
        history.add(new HistorySegment(
                "Mechanical Computing Era",
                "Charles Babbage designed the Analytical Engine (1837), considered the first concept of a programmable computer, and Ada Lovelace wrote the first algorithm for it. Punch card-based tabulating machines, like those used in the 1890 U.S. Census, laid the foundation for early data processing.",
                "https://www.i-programmer.info/images/stories/BabBag/whatifbabage/difference.JPG"
        ));
        history.add(new HistorySegment(
                "Electromechanical and Early Electronic Computing",
                "Computers transitioned from mechanical relays to vacuum tubes, with early machines like Zuse’s Z3 (1941) and ENIAC (1945) demonstrating programmable computing. Alan Turing's work on theoretical computation (1936) and the concept of stored programs (von Neumann architecture) revolutionized computing logic.",
                "https://cdn.sanity.io/images/i2z87pbo/production/a9132a54f148d9eef366dbf5f7a8cb5c25603971-2500x1597.webp"
        ));
        history.add(new HistorySegment(
                "Mainframe and Early Software Era",
                "Large mainframe computers, like the IBM 360 (1964), dominated business and scientific computing, while programming languages such as FORTRAN (1957) and COBOL (1959) emerged. The development of time-sharing systems and early networking laid the groundwork for modern computing.",
                "https://substackcdn.com/image/fetch/f_auto,q_auto:good,fl_progressive:steep/https%3A%2F%2Fbucketeer-e05bbc84-baa3-437e-9518-adb32be77984.s3.amazonaws.com%2Fpublic%2Fimages%2F6ee2471a-01e4-4f49-a840-7b0fd1677217_2048x1536.jpeg"
        ));
        history.add(new HistorySegment(
                "Personal Computing and Networking Era",
                "The invention of the microprocessor (1971, Intel 4004) led to affordable personal computers like the Apple II (1977) and IBM PC (1981). The rise of graphical user interfaces, the internet’s commercialization, and the introduction of programming languages like C (1972) and Java (1995) transformed computing accessibility.",
                "https://substackcdn.com/image/fetch/f_auto,q_auto:good,fl_progressive:steep/https%3A%2F%2Fsubstack-post-media.s3.amazonaws.com%2Fpublic%2Fimages%2Fcf3e791a-6b7b-4615-9002-de2a97490677_1024x1024.webp"
        ));
        history.add(new HistorySegment(
                "Web, Mobile, and AI Era",
                "The explosion of the internet, cloud computing, and mobile devices revolutionized how people interact with technology, with platforms like Google (1998) and Facebook (2004) reshaping communication. Advances in AI, quantum computing, and deep learning continue to push the boundaries of what computers can achieve.",
                "https://incubator.ucf.edu/wp-content/uploads/2023/07/artificial-intelligence-new-technology-science-futuristic-abstract-human-brain-ai-technology-cpu-central-processor-unit-chipset-big-data-machine-learning-cyber-mind-domination-generative-ai-scaled-1.jpg"
        ));

        iterator = history.iterator();
        HistorySegment first = iterator.next(); //Get the first history segment
        showHistory(first);
    }

    public void showHistory(HistorySegment hs) {
        titleLbl.setText(hs.getTitle());
        historyLbl.setText(hs.getDescription());
        historyIV.setImage(hs.getImage());
        timelineLbl.setText(history.toString(iterator));
    }

}
