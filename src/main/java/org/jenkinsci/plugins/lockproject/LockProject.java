package org.jenkinsci.plugins.lockproject;

import hudson.Extension;
import hudson.ExtensionList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jenkins.model.GlobalConfiguration;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;
import org.kohsuke.stapler.StaplerRequest;

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
    this.getDescriptor().save();
  }

  public Boolean getLockFreestyle() {
    return lockFreestyle;
  }

  @DataBoundSetter
  public void setLockFreestyle(Boolean lockFreestyle) {
    this.lockFreestyle = lockFreestyle;
    logger.log(Level.INFO, "Freesytle lock set to " + lockFreestyle + " by " + "admin");
  }

  public Boolean getLockPipeline() {
    return lockPipeline;
  }

  @DataBoundSetter
  public void setLockPipeline(Boolean lockPipeline) {
    this.lockPipeline = lockPipeline;
    logger.log(Level.INFO, "Pipeline lock set to " + lockPipeline + " by " + "admin");
  }

  public Boolean getLockMultibranch() {
    return lockMultibranch;
  }

  @DataBoundSetter
  public void setLockMultibranch(Boolean lockMultibranch) {
    this.lockMultibranch = lockMultibranch;
    logger.log(Level.INFO, "Multibranch lock set to " + lockMultibranch + " by " + "admin");
  }

  public Boolean getLockOrganization() {
    return lockOrganization;
  }

  @DataBoundSetter
  public void setLockOrganization(Boolean lockOrganization) {
    this.lockOrganization = lockOrganization;
    logger.log(Level.INFO, "Organization lock set to " + lockOrganization + " by " + "admin");
  }

  public Boolean getLockMaven() {
    return lockMaven;
  }

  @DataBoundSetter
  public void setLockMaven(Boolean lockMaven) {
    this.lockMaven = lockMaven;
    logger.log(Level.INFO, "Maven lock set to " + lockMaven + " by " + "admin");
  }

  public Boolean getAllowAdmins() {
    return allowAdmins;
  }

  @DataBoundSetter
  public void setAllowAdmins(Boolean allowAdmins) {
    this.allowAdmins = allowAdmins;
    logger.log(Level.INFO, "Allow admins set to " + allowAdmins);
  }

  @Override
  public boolean configure(StaplerRequest req, JSONObject jsonObject) throws FormException {
    req.bindJSON(this, jsonObject);
    save();
    return true;
  }
}
