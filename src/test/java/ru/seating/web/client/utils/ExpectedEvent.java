package ru.seating.web.client.utils;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.EventBus;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.annotation.Nonnull;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by Константин on 08.01.2015.
 */
public class ExpectedEvent implements TestRule {

    private Event expectedEvent;

    private EventBus eventBus;

    private boolean wasEvent = false;

    public static ExpectedEvent none() {
        return new ExpectedEvent();
    }

    public ExpectedEvent() {
    }

    @Override
    public Statement apply(Statement statement, Description description) {
        return new ExpectedEventStatement(statement);
    }

    public void expect(@Nonnull Event event, @Nonnull EventBus eventBus) {
        Preconditions.checkNotNull(event);
        Preconditions.checkNotNull(eventBus);
        this.expectedEvent = event;
        this.eventBus = eventBus;
        configMock();
    }

    public class ExpectedEventStatement extends Statement {

        private final Statement next;

        public ExpectedEventStatement(Statement base) {
            this.next = base;
        }

        @Override
        public void evaluate() throws Throwable {
            next.evaluate();
            if (expectedEvent != null && !wasEvent) {
                fail("Expected event wasn't fired");
            }
        }
    }

    private void configMock() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Event event = (Event)invocationOnMock.getArguments()[0];
                if (expectedEvent != null && expectedEvent.equals(event)) {
                    wasEvent = true;
                }
                return null;
            }
        }).when(eventBus).fireEvent(any(Event.class));
    }
}
