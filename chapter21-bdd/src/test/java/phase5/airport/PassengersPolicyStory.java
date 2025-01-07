package phase5.airport;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

//JBehave 스토리 클래스는 반드시 JUnitStory를 상속해야 한다
public class PassengersPolicyStory extends JUnitStory {

    @Override
    public Configuration configuration() {
//        리포트 콘솔 출력
        return new MostUsefulConfiguration().useStoryReporterBuilder(
                new StoryReporterBuilder().withDefaultFormats()
                        .withFormats(Format.CONSOLE)
        );
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
//        스텝 인스턴스 지정
        return new InstanceStepsFactory(configuration(), new PassengersPolicy());
    }
}
