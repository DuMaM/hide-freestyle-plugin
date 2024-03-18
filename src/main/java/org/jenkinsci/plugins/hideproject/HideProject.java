package org.jenkinsci.plugins.hideproject;

import hudson.Extension;
import hudson.ExtensionList;

import jenkins.model.GlobalConfiguration;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;

@Extension
public class HideProject extends GlobalConfiguration {
  private String parameter1;
  private String parameter2;

  /** @return the singleton instance */
  public static HideProject get() {
    return ExtensionList.lookupSingleton(HideProject.class);
  }

  @DataBoundConstructor
  public HideProject() {
    // When Jenkins is restarted, load any saved configuration from disk.
    load();
  }

  public String getParameter1() {
    return parameter1;
  }

  @DataBoundSetter
  public void setParameter1(String parameter1) {
    this.parameter1 = parameter1;
    save();
  }

  public String getParameter2() {
    return parameter2;
  }

  @DataBoundSetter
  public void setParameter2(String parameter2) {
    this.parameter2 = parameter2;
    save();
  }

  // @Extension
  // public static final class DescriptorImpl extends Descriptor<HideProject> {
  // @Override
  // public String getDisplayName() {
  // return "Hide Project";
  // }

  // private String parameter1;

  // /**
  // * In order to load the persisted global configuration, you have to
  // * call load() in the constructor.
  // */
  // public DescriptorImpl() {
  // load();
  // }

  // @Override
  // public boolean configure(StaplerRequest req, JSONObject formData) throws
  // FormException {
  // // To persist global configuration information,
  // // set that to properties and call save().
  // parameter1 = formData.getString("parameter1");
  // // ^Can also use req.bindJSON(this, formData);
  // // (easier when there are many fields; need set* methods for this, like
  // // setUseFrench)
  // save();
  // return super.configure(req, formData);
  // }

  // // public HideProject getGlobalConfig() {
  // // return GlobalConfiguration.all().get(HideProject.class);
  // // }
  // }
}
