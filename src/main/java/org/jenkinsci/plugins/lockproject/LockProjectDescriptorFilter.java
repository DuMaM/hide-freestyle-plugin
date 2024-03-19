package org.jenkinsci.plugins.lockproject;

import hudson.Extension;
import hudson.model.Descriptor;
import hudson.model.DescriptorVisibilityFilter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import jenkins.model.Jenkins;

@Extension
public class LockProjectDescriptorFilter extends DescriptorVisibilityFilter {
    private static LockProject instance = LockProject.get();

    // map which translate project name to class name
    private Map<String, BooleanSupplier> projectTypes = new HashMap<>();
    private static final Logger logger = Logger.getLogger(LockProjectDescriptorFilter.class.getName());

    public LockProjectDescriptorFilter() {
        projectTypes.put("hudson.model.FreeStyleProject", () -> instance.getLockFreestyle());
        projectTypes.put("org.jenkinsci.plugins.workflow.job.WorkflowJob", () -> instance.getLockPipeline());
        projectTypes.put("jenkins.branch.MultiBranchProject", () -> instance.getLockMultibranch());
        projectTypes.put("jenkins.branch.OrganizationFolder", () -> instance.getLockOrganization());
        projectTypes.put("hudson.maven.MavenModuleSet", () -> instance.getLockMaven());

        logger.log(Level.FINEST, "LockProjectDescriptorFilter created");
    }

    // return true to allow
    @Override
    public boolean filter(Object context, Descriptor descriptor) {
        if (descriptor == null) {
            return true;
        }

        String id = descriptor.getId();
        if (id == null || id.isEmpty()) {
            return true;
        }

        if (!projectTypes.containsKey(id)) {
            return true;
        }

        BooleanSupplier lockFn = projectTypes.get(descriptor.getId());
        boolean lock = lockFn.getAsBoolean();

        return !lock || Jenkins.get().hasPermission(Jenkins.ADMINISTER) && instance.getAllowAdmins();
    }
}
