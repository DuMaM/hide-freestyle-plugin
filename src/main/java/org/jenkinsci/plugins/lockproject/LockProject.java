package org.jenkinsci.plugins.lockproject;

import hudson.Extension;
import hudson.ExtensionList;

import java.util.logging.Level;
import java.util.logging.Logger;

import jenkins.model.GlobalConfiguration;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;

@Extension
public class LockProject extends GlobalConfiguration {

  private Boolean lockFreestyle;
  private Boolean lockPipeline;
  private Boolean lockMultibranch;
  private Boolean lockOrganization;
  private Boolean lockMaven;
  private Boolean allowAdmins;

  private static final Logger logger = Logger.getLogger(LockProject.class.getName());

  /** @return the singleton instance */
  public static LockProject get() {
    return ExtensionList.lookupSingleton(LockProject.class);
  }

  @DataBoundConstructor
  public LockProject() {
    // When Jenkins is restarted, load any saved configuration from disk.
    load();

    logger.log(Level.INFO, "LockProject plugin is loaded created");
  }

  public Boolean getLockFreestyle() {
    return lockFreestyle;
  }

  @DataBoundSetter
  public void setLockFreestyle(Boolean lockFreestyle) {
    this.lockFreestyle = lockFreestyle;
    save();
  }

  public Boolean getLockPipeline() {
    return lockPipeline;
  }

  @DataBoundSetter
  public void setLockPipeline(Boolean lockPipeline) {
    this.lockPipeline = lockPipeline;
    save();
  }

  public Boolean getLockMultibranch() {
    return lockMultibranch;
  }

  @DataBoundSetter
  public void setLockMultibranch(Boolean lockMultibranch) {
    this.lockMultibranch = lockMultibranch;
    save();
  }

  public Boolean getLockOrganization() {
    return lockOrganization;
  }

  @DataBoundSetter
  public void setLockOrganization(Boolean lockOrganization) {
    this.lockOrganization = lockOrganization;
    save();
  }

  public Boolean getLockMaven() {
    return lockMaven;
  }

  @DataBoundSetter
  public void setLockMaven(Boolean lockMaven) {
    this.lockMaven = lockMaven;
    save();
  }

  public Boolean getAllowAdmins() {
    return allowAdmins;
  }

  @DataBoundSetter
  public void setAllowAdmins(Boolean allowAdmins) {
    this.allowAdmins = allowAdmins;
    save();
  }
}
