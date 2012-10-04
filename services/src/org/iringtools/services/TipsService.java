/**
 * 
 */
package org.iringtools.services;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.iringtools.mapping.ClassesList;
import org.iringtools.mapping.CommodityList;
import org.iringtools.mapping.CommodityTipList;
import org.iringtools.mapping.OrdinalList;
import org.iringtools.mapping.PatternsList;
import org.iringtools.mapping.Role;
import org.iringtools.mapping.RoleList;
import org.iringtools.mapping.TemplateMapList;
import org.iringtools.mapping.TipsList;
import org.iringtools.security.AuthorizationException;
import org.iringtools.services.core.TipProvider;

/**
 * @author vmallire
 * 
 */
@Path("/")
@Produces("application/xml")
@Consumes({"application/json", "application/xml"})
public class TipsService extends AbstractService
{

  private static final Logger logger = Logger.getLogger(AbstractService.class);
  private final String SERVICE_NAME = "TipService";
  private TemplateMapList tmt;
  private PatternsList pl;
  private CommodityList coml;
  private CommodityTipList comTipl;
  private RoleList rl;
  private TipsList tipl;
  private Role r;
  private OrdinalList ol;
  private ClassesList cl; 

  //............................Commodity Get,POST Put and Delete..............................
  @GET
  @Path("/commodity")
  public Response getCommodity()
  {

    try
    {
      initService(SERVICE_NAME);
    }
    catch (Exception e)
    {
      logger.error("Error initializing TipService: " + e);
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }

    try
    {  logger.debug("commodity details"); 
      TipProvider TipProvider = new TipProvider(settings);
      coml = TipProvider.getCommodity();

    }
    catch (Exception e)
    {

      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }

    return Response.ok().entity(coml).build();
  }
  
  // @Path("/commodity/{id}/{name}/{rdlClassId}/Submit")

  @POST
  @Path("/commodity/newcommodity")
  @Consumes({"application/json", "application/xml"})
  @Produces("application/xml")
  public Response newCommodity(CommodityList comlist)
  {
    try
    {
      initService(SERVICE_NAME);
    }
    catch (AuthorizationException e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }
    
    try
    { 
      TipProvider TipProvider = new TipProvider(settings);
      TipProvider.postCommodity(comlist); 
     
    }
    catch (Exception e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }
    
    return Response.ok().entity(comlist).build();
  }
  
  @PUT
  @Path("/commodity/updatecommodity")
  @Consumes({"application/json", "application/xml"})
  public Response updateCommodity(CommodityList comlist)
  {
    try
    {
      initService(SERVICE_NAME);
    }
    catch (AuthorizationException e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }
    
    try
    { 
      TipProvider TipProvider = new TipProvider(settings);
      TipProvider.putCommodity(comlist); 
     
    }
    catch (Exception e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }
    
    return Response.ok().entity(comlist).build();
  }

  @DELETE
  @Path("/commodity/delete/{id}")
  @Produces("application/xml")
  public Response DeleteCommodity(@PathParam("id") String id)
  {
    try
    {
      initService(SERVICE_NAME);
    }
    catch (AuthorizationException e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }
    
    try
    { 
      TipProvider TipProvider = new TipProvider(settings);
      TipProvider.deleteCommodity(id); 
     
    }
    catch (Exception e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }
    return Response.ok().entity(id).build();
  }
  
  //............................CommodityTip Get and POST..............................
  @GET
  @Path("/commoditytip")
  public Response getCommodityTip()
  {

    try
    {
      initService(SERVICE_NAME);
    }
    catch (Exception e)
    {
      logger.error("Error initializing TipService: " + e);
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }

    try
    {
      TipProvider TipProvider = new TipProvider(settings);
      comTipl = TipProvider.getCommodityTip();

    }
    catch (Exception e)
    {

      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }

    return Response.ok().entity(comTipl).build();
  }
  
  @POST
  @Path("/commoditytip/newcommoditytip")
  @Consumes({"application/json", "application/xml"})
  @Produces("application/xml")
  public Response newCommodityTip(CommodityTipList comtip)
  {
    try
    {
      initService(SERVICE_NAME);
    }
    catch (AuthorizationException e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }
    
    try
    { 
      TipProvider TipProvider = new TipProvider(settings);
      TipProvider.postCommodityTip(comtip); 
     
    }
    catch (Exception e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }
    
    return Response.ok().entity(comtip).build();
  }
  
  @DELETE
  @Path("/commoditytip/delete/{tid}/{comid}")
  @Produces("application/xml")
  public Response DeleteCommodityTip(@PathParam("tid") String tid, @PathParam("comid") String comid)
  {
    try
    {
      initService(SERVICE_NAME);
    }
    catch (AuthorizationException e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }
    
    try
    { 
      TipProvider TipProvider = new TipProvider(settings);
      TipProvider.deleteCommodityTip(tid, comid); 
     
    }
    catch (Exception e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }
    
    return Response.ok().entity(tid).build();
  }
  
  //............................Tip Get and POST..............................
  
  @GET
  @Path("/tips")
  public Response getTips()
  {

    try
    {
      initService(SERVICE_NAME);
    }
    catch (Exception e)
    {
      logger.error("Error initializing TipService: " + e);
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }

    try
    {
      TipProvider TipProvider = new TipProvider(settings);
      tipl = TipProvider.getTips();

    }
    catch (Exception e)
    {

      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }

    return Response.ok().entity(tipl).build();
  }
  @POST
  @Path("/tips/newtips")
  @Consumes({"application/json", "application/xml"})
  public Response newTip(TipsList tiplist)
  {
    try
    {
      initService(SERVICE_NAME);
    }
    catch (AuthorizationException e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }
    
    try
    { 
      TipProvider TipProvider = new TipProvider(settings);
      TipProvider.postTips(tiplist); 
     
    }
    catch (Exception e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }
    
    return Response.ok().entity(tiplist).build();
  }
  
  @PUT
  @Path("/tips/updatetips")
  @Consumes({"application/json", "application/xml"})
  public Response updateTip(TipsList tiplist)
  {
    try
    {
      initService(SERVICE_NAME);
    }
    catch (AuthorizationException e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }
    
    try
    { 
      TipProvider TipProvider = new TipProvider(settings);
      TipProvider.putTips(tiplist); 
     
    }
    catch (Exception e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }
    
    return Response.ok().entity(tiplist).build();
  }
  
  @PUT
  @Path("/tips/updatetips/{id}")
  @Consumes({"application/json", "application/xml"})
  public Response updateOneTip(TipsList tiplist)
  {
    try
    {
      initService(SERVICE_NAME);
    }
    catch (AuthorizationException e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }
    
    try
    { 
      TipProvider TipProvider = new TipProvider(settings);
      TipProvider.putTips(tiplist); 
     
    }
    catch (Exception e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }
    
    return Response.ok().entity(tiplist).build();
  }
  
  
  @GET
  @Path("tips/{id}")
  public Response getTips(
      @PathParam("id") String id)
  {    
    try
    {
      initService(SERVICE_NAME);
    }
    catch (AuthorizationException e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }
    
    try
    {
      TipProvider TipProvider = new TipProvider(settings);    
      tipl = TipProvider.getTip(id);
    }
    catch (Exception e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }
    
    return Response.ok().entity(tipl).build();
  }
  
  
  @GET
  @Path("tips/{id}/patterns")
  public Response getTipPatterns(
      @PathParam("id") String id)
  {    
    try
    {
      initService(SERVICE_NAME);
    }
    catch (AuthorizationException e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }
    
    try
    {
      TipProvider TipProvider = new TipProvider(settings);    
      tipl = TipProvider.getTipPatterns(id);
    }
    catch (Exception e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }
    
    return Response.ok().entity(tipl).build();
  }
  
  @DELETE
  @Path("/tips/delete/{id}")
  @Produces("application/xml")
  public Response DeleteTip(@PathParam("id") String id)
  {
    try
    {
      initService(SERVICE_NAME);
    }
    catch (AuthorizationException e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }
    
    try
    { 
      TipProvider TipProvider = new TipProvider(settings);
      TipProvider.deleteTip(id); 
     
    }
    catch (Exception e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }
    return Response.ok().entity(id).build();
  }
  
  
  /*@GET
  @Path("tips/{id}/patterns/{pid}")
  public Response getTipPattOrdinal(
      @PathParam("id") String id, @PathParam("pid") String pid)
  {    
    try
    {
      initService(SERVICE_NAME);
    }
    catch (AuthorizationException e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }
    
    try
    {
      TipProvider TipProvider = new TipProvider(settings);    
      ol = TipProvider.getTipPattOrdinal(id, pid);
    }
    catch (Exception e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }
    
    return Response.ok().entity(ol).build();
  }*/
  //............................Patterns Get and POST..............................
  

  @GET
  @Path("/patterns")
  public Response getPatterns()
  {

    try
    {
      initService(SERVICE_NAME);
    }
    catch (Exception e)
    {
      logger.error("Error initializing TipService: " + e);
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }

    try
    {
      TipProvider TipProvider = new TipProvider(settings);
      pl = TipProvider. getPattern();

    }
    catch (Exception e)
    {

      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }

    return Response.ok().entity(pl).build();
  }

  @POST
  @Path("/patterns/newpatterns")
  @Consumes({"application/json", "application/xml"})
  @Produces("application/xml")
  public Response NewPatterns(PatternsList patList)
  {
    try
    {
      initService(SERVICE_NAME);
    }
    catch (AuthorizationException e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }
    
    try
    { 
      TipProvider TipProvider = new TipProvider(settings);
      TipProvider.postPatterns(patList); 
     
    }
    catch (Exception e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }
    
    return Response.ok().entity(patList).build();
  }

  @PUT
  @Path("/patterns/updatepatterns")
  @Consumes({"application/json", "application/xml"})
  public Response updatePatterns(PatternsList patList)
  {
    try
    {
      initService(SERVICE_NAME);
    }
    catch (AuthorizationException e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }
    
    try
    { 
      TipProvider TipProvider = new TipProvider(settings);
      TipProvider.putPatterns(patList); 
     
    }
    catch (Exception e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }
    
    return Response.ok().entity(patList).build();
  }

  @DELETE
  @Path("/patterns/delete/{id}")
  @Produces("application/xml")
  public Response DeletePatterns(@PathParam("id") String id)
  {
    try
    {
      initService(SERVICE_NAME);
    }
    catch (AuthorizationException e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }
    
    try
    { 
      TipProvider TipProvider = new TipProvider(settings);
      TipProvider.deletePatterns(id); 
     
    }
    catch (Exception e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }
    return Response.ok().entity(id).build();
  }
  
  @GET
  @Path("/patterns/{pid}")
  public Response getPatterns(
      @PathParam("pid") String pid)
  {    
    try
    {
      initService(SERVICE_NAME);
    }
    catch (AuthorizationException e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }
    
    try
    {
      TipProvider TipProvider = new TipProvider(settings);    
      pl = TipProvider.getPatterns(pid);
    }
    catch (Exception e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }
    
    return Response.ok().entity(pl).build();
  }
  
  @GET
  @Path("/patterns/{pid}/ordinals")
  public Response getPattOrdinals(
      @PathParam("pid") String pid)
  {    
    try
    {
      initService(SERVICE_NAME);
    }
    catch (AuthorizationException e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }
    
    try
    {
      TipProvider TipProvider = new TipProvider(settings);    
      ol = TipProvider.getPattOrdinal(pid);
    }
    catch (Exception e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }
    
    return Response.ok().entity(ol).build();
  }
  
  @POST
  @Path("/patterns/newordinals")
  @Consumes({"application/json", "application/xml"})
  public Response newOrdinal(OrdinalList ordlist)
  {
    try
    {
      initService(SERVICE_NAME);
    }
    catch (AuthorizationException e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }
    
    try
    { 
      TipProvider TipProvider = new TipProvider(settings);
      TipProvider.postOrdinal(ordlist); 
     
    }
    catch (Exception e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }
    
    return Response.ok().entity(ordlist).build();
  }
  
  @PUT
  @Path("/patterns/updateordinals")
  @Consumes({"application/json", "application/xml"})
  public Response updateOrdinal(OrdinalList olist)
  {
    try
    {
      initService(SERVICE_NAME);
    }
    catch (AuthorizationException e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }
    
    try
    { 
      TipProvider TipProvider = new TipProvider(settings);
      TipProvider.putOrdinals(olist); 
     
    }
    catch (Exception e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }
    
    return Response.ok().entity(olist).build();
  }
  
  
  @DELETE
  @Path("/patterns/ordinal/delete/{pid}/{ord}")
  @Produces("application/xml")
  public Response DeletePatterns(@PathParam("pid") String pid, @PathParam("ord") int ord)
  {
    try
    {
      initService(SERVICE_NAME);
    }
    catch (AuthorizationException e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }
    
    try
    { 
      TipProvider TipProvider = new TipProvider(settings);
      TipProvider.deleteOrdinal(pid, ord); 
     
    }
    catch (Exception e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }
    return Response.ok().entity(pid).build();
  }
  
  //............................Template Get and POST..............................
  @GET
  @Path("/templates")
  public Response getTempalte()
  {

    try
    {
      initService(SERVICE_NAME);
    }
    catch (Exception e)
    {
      logger.error("Error initializing TipService: " + e);
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }

    try
    {
      TipProvider TipProvider = new TipProvider(settings);
      tmt = TipProvider.getTemplate(); // getPattern();

    }
    catch (Exception e)
    {

      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }

    return Response.ok().entity(tmt).build();
  }
  

  @POST
  @Path("/templates/newtemplates")
  @Consumes({"application/json", "application/xml"})
  public Response newTemplate(TemplateMapList tmlist)
  {
    try
    {
      initService(SERVICE_NAME);
    }
    catch (AuthorizationException e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }
    
    try
    { 
      TipProvider TipProvider = new TipProvider(settings);
      TipProvider.postTemplate(tmlist); 
     
    }
    catch (Exception e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }
    
    return Response.ok().entity(tmlist).build();
  }
  @PUT
  @Path("/templates/updatetemplates")
  @Consumes({"application/json", "application/xml"})
  public Response updateTemplates(TemplateMapList tlist)
  {
    try
    {
      initService(SERVICE_NAME);
    }
    catch (AuthorizationException e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }
    
    try
    { 
      TipProvider TipProvider = new TipProvider(settings);
      TipProvider.putTemplates(tlist); 
     
    }
    catch (Exception e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }
    
    return Response.ok().entity(tlist).build();
  }

  
  @DELETE
  @Path("/templates/delete/{id}")
  @Produces("application/xml")
  public Response DeleteTempalte(@PathParam("id") String id)
  {
    try
    {
      initService(SERVICE_NAME);
    }
    catch (AuthorizationException e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }
    
    try
    { 
      TipProvider TipProvider = new TipProvider(settings);
      TipProvider.deleteTemplate(id); 
     
    }
    catch (Exception e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }
    return Response.ok().entity(id).build();
  }
  
  @GET
  @Path("/templates/{temid}")
  public Response getOneTemplate(@PathParam("temid") String temid)
  {

    try
    {
      initService(SERVICE_NAME);
    }
    catch (Exception e)
    {
      logger.error("Error initializing TipService: " + e);
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }

    try
    {
      TipProvider TipProvider = new TipProvider(settings);
      tmt = TipProvider.getOneTemplate(temid); // getPattern();

    }
    catch (Exception e)
    {

      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }

    return Response.ok().entity(tmt).build();
  }
  @GET
  @Path("/templates/{temid}/roles")
  public Response getTemplateRole(@PathParam("temid") String temid)
  {

    try
    {
      initService(SERVICE_NAME);
    }
    catch (Exception e)
    {
      logger.error("Error initializing TipService: " + e);
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }

    try
    {
      TipProvider TipProvider = new TipProvider(settings);
      tmt = TipProvider.getTemplateRole(temid); 

    }
    catch (Exception e)
    {

      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }

    return Response.ok().entity(tmt).build();
  }
  
  @GET
  @Path("/templates/{temid}/roles/{rid}")
  public Response getTemplateParticularRole(@PathParam("temid") String temid, @PathParam("rid") String rid)
  {

    try
    {
      initService(SERVICE_NAME);
    }
    catch (Exception e)
    {
      logger.error("Error initializing TipService: " + e);
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }

    try
    {
      TipProvider TipProvider = new TipProvider(settings);
      rl = TipProvider.getTemplateParticularRole(temid, rid); 

    }
    catch (Exception e)
    {

      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }

    return Response.ok().entity(rl).build();
  }
  //............................Role Get and POST..............................
  @GET
  @Path("/roles")
  public Response getRoles()
  {

    try
    {
      initService(SERVICE_NAME);
    }
    catch (Exception e)
    {
      logger.error("Error initializing TipService: " + e);
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }

    try
    {
      TipProvider TipProvider = new TipProvider(settings);
      rl = TipProvider.getRoles();

    }
    catch (Exception e)
    {

      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }

    return Response.ok().entity(rl).build();
  }
  
  @GET
  @Path("/roles/{rid}")
  public Response getRole( @PathParam("rid") String rid)
  {

    try
    {
      initService(SERVICE_NAME);
    }
    catch (Exception e)
    {
      logger.error("Error initializing TipService: " + e);
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }

    try
    {
      TipProvider TipProvider = new TipProvider(settings);
      rl = TipProvider.getRole(rid);

    }
    catch (Exception e)
    {

      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }

    return Response.ok().entity(rl).build();
  }
  

  @POST
  @Path("/roles/newroles")
  @Consumes({"application/json", "application/xml"})
  public Response newRoles(RoleList rolist)
  {
    try
    {
      initService(SERVICE_NAME);
    }
    catch (AuthorizationException e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }
    
    try
    { 
      TipProvider TipProvider = new TipProvider(settings);
      TipProvider.postRole(rolist); 
     
    }
    catch (Exception e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }
    
    return Response.ok().entity(rolist).build();
  }

  @PUT
  @Path("/roles/updateroles")
  @Consumes({"application/json", "application/xml"})
  public Response updateRoles(RoleList rlist)
  {
    try
    {
      initService(SERVICE_NAME);
    }
    catch (AuthorizationException e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }
    
    try
    { 
      TipProvider TipProvider = new TipProvider(settings);
      TipProvider.putRoles(rlist); 
     
    }
    catch (Exception e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }
    
    return Response.ok().entity(rlist).build();
  }
  
  @DELETE
  @Path("/roles/delete/{tid}/{rid}")
  @Produces("application/xml")
  public Response DeleteRoles(@PathParam("tid") String tid, @PathParam("rid") String rid)
  {
    try
    {
      initService(SERVICE_NAME);
    }
    catch (AuthorizationException e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }
    
    try
    { 
      TipProvider TipProvider = new TipProvider(settings);
      TipProvider.deleteRoles(tid, rid); 
     
    }
    catch (Exception e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }
    return Response.ok().entity(tid).build();
  }

  //............................Classes Get and POST..............................
  @GET
  @Path("/classes")
  public Response getClasses()
  {

    try
    {
      initService(SERVICE_NAME);
    }
    catch (Exception e)
    {
      logger.error("Error initializing TipService: " + e);
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }

    try
    {
      TipProvider TipProvider = new TipProvider(settings);
      cl = TipProvider.getClasses();

    }
    catch (Exception e)
    {

      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }

    return Response.ok().entity(cl).build();
  }
  @GET
  @Path("/classes/{cid}")
  public Response getClassesone(@PathParam("cid") String cid)
  {

    try
    {
      initService(SERVICE_NAME);
    }
    catch (Exception e)
    {
      logger.error("Error initializing TipService: " + e);
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }

    try
    {
      TipProvider TipProvider = new TipProvider(settings);
      cl = TipProvider.getClasses(cid);

    }
    catch (Exception e)
    {

      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }

    return Response.ok().entity(cl).build();
  }
  

  @POST
  @Path("/classes/newclass")
  @Consumes({"application/json", "application/xml"})
  public Response newClass(ClassesList cllist)
  {
    try
    {
      initService(SERVICE_NAME);
    }
    catch (AuthorizationException e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }
    
    try
    { 
      TipProvider TipProvider = new TipProvider(settings);
      TipProvider.postClass(cllist); 
     
    }
    catch (Exception e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }
    
    return Response.ok().entity(cllist).build();
  }
  
  @PUT
  @Path("/classes/updateclasses")
  @Consumes({"application/json", "application/xml"})
  public Response updateClasses(ClassesList cllist)
  {
    try
    {
      initService(SERVICE_NAME);
    }
    catch (AuthorizationException e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }
    
    try
    { 
      TipProvider TipProvider = new TipProvider(settings);
      TipProvider.putClasses(cllist); 
     
    }
    catch (Exception e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }
    
    return Response.ok().entity(cllist).build();
  }
  
  @DELETE
  @Path("/classes/delete/{id}")
  @Produces("application/xml")
  public Response DeleteClasses(@PathParam("id") String id)
  {
    try
    {
      initService(SERVICE_NAME);
    }
    catch (AuthorizationException e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e);
    }
    
    try
    { 
      TipProvider TipProvider = new TipProvider(settings);
      TipProvider.deleteClasses(id); 
     
    }
    catch (Exception e)
    {
      return prepareErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
    }
    return Response.ok().entity(id).build();
  }
}
