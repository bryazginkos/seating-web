package ru.seating.web.client.application.persons.editGroup;

import com.google.inject.Inject;
import org.jukito.JukitoRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.seating.web.client.application.persons.editgroup.EditGroupPresenter;
import ru.seating.web.client.exception.BusinessException;
import ru.seating.web.client.model.Group;
import ru.seating.web.client.model.GroupColor;
import ru.seating.web.client.model.Model;
import ru.seating.web.client.model.ModelManager;

import java.util.Arrays;

import static org.mockito.Mockito.*;

@RunWith(JukitoRunner.class)
public class EditGroupPresenterTest {

    @Inject
    EditGroupPresenter editGroupPresenter;

    @Before
    public void clearModel() {
        ModelManager.clear();
    }

    @Test
    public void testInitForCreatingWithFreeColors() throws BusinessException {
        prepareModelWithFreeColors();
        editGroupPresenter.initForCreating();
        verify(editGroupPresenter.getView()).setColorBox(anyCollection());
        verify(editGroupPresenter.getView()).setGroupName("");
    }

    private void prepareModelWithFreeColors() throws BusinessException {
        Model model = ModelManager.getModel();
        model.addGroup(new Group("Red group", GroupColor.RED));
    }

}
