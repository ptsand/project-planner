/*
Author He
10.12.2021
 */

package dk.kea.projectplanner.services;


import dk.kea.projectplanner.ProjectPlannerApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = ProjectPlannerApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:integration-test.properties")
public class ProjectServiceIntegrationTest {

    private static final LocalDateTime BASE_DATE = LocalDateTime.of(2000,10,21,1,0,0);
/*
    @Autowired
    private ProjectService projectService;
    @Autowired
    private SubProjectService subProjectService;

    @Before
    public void setUp(){
        // Can create ProjectModel object here to make sure there is something in the db for testing methods that rely on db not being empty
    }

    @Test
    @Transactional // Automatically rolls back changes when test is done (fx. removes added row from db)
    public void createProjectTest(){
        assertNotNull(projectService);
        var projectModel = createProjectModel();

        projectService.createProject(projectModel);

        assertNotEquals("expected id to be set by autoincrement id in database",0,projectModel.getId());
        assertNotEquals("expected id to be set by autoincrement id in database",0,projectModel.getDateTimeId());
        assertNotEquals("expected id to be set by autoincrement id in database",0,projectModel.getActivityId());
    }

    private ProjectModel createProjectModel() {
        var p = new ProjectModel();
        p.setActualEndDate(BASE_DATE);
        p.setActualStartDate(BASE_DATE);
        p.setDeadline(BASE_DATE);
        p.setPlannedStartDate(BASE_DATE);
        p.setName("testprjname");
        return p;
    }

    // TODO: Test deleteById
    /*
    @Test
    @Transactional
    @Rollback(value = false)
    public void addSubProjectTest() {
        var projectModel = projectService.createProject(createProjectModel());
        var subProjectModel = subProjectService.createSubProject(createSubProjectModel());

        var updatedProject = projectService.addSubProjectToProject(projectModel, subProjectModel);
        projectService.populateSubprojects(updatedProject.getId());
        assertTrue(updatedProject.containsSubProject(subProjectModel));
    }

    private SubProjectModel createSubProjectModel() {
        var s = new SubProjectModel();
        s.setActualEndDate(BASE_DATE);
        s.setActualStartDate(BASE_DATE);
        s.setDeadline(BASE_DATE);
        s.setPlannedStartDate(BASE_DATE);
        s.setName("testsubprjname");
        return s;
    } */
}