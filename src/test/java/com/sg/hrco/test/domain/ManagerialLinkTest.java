package com.sg.hrco.test.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.sg.hrco.test.web.rest.TestUtil;

public class ManagerialLinkTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ManagerialLink.class);
        ManagerialLink managerialLink1 = new ManagerialLink();
        managerialLink1.setId(1L);
        ManagerialLink managerialLink2 = new ManagerialLink();
        managerialLink2.setId(managerialLink1.getId());
        assertThat(managerialLink1).isEqualTo(managerialLink2);
        managerialLink2.setId(2L);
        assertThat(managerialLink1).isNotEqualTo(managerialLink2);
        managerialLink1.setId(null);
        assertThat(managerialLink1).isNotEqualTo(managerialLink2);
    }
}
