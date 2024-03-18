package org.jenkinsci.plugins.hideproject;

import hudson.Extension;
import hudson.model.Descriptor;
import hudson.model.DescriptorVisibilityFilter;

@Extension
public class HideProjectDescriptorFilter extends DescriptorVisibilityFilter {

    public static final boolean disableFlag = false;

    // return true to allow
    @Override
    public boolean filter(Object context, Descriptor descriptor) {
        if (disableFlag) {
            return true;
        }

        if (descriptor.getId() != null && descriptor.getId().equals("hudson.model.FreeStyleProject")) {
            return false;
        }
        return true;
    }
}
