package org.elsys.ip.rest.resource;

import org.elsys.ip.rest.model.Tank;
import org.elsys.ip.rest.service.TankService;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.*;
import java.util.List;

@Path("tanks")
public class TankResource {

    private TankService tankService = new TankService();

    /**
     * Returns all the available objects.
     *
     * @return List<Tank>
     */
    @GET
    @Produces("application/json")
    public List<Tank> getTankList(@Context UriInfo ui) {
        MultivaluedMap<String, String> queryParams = ui.getQueryParameters();

        if (!queryParams.isEmpty()) {
            if (queryParams.containsKey("id")) {
                return tankService.getTanksById(queryParams);
            }
            return tankService.getTanksByFilter(queryParams);
        }
        return tankService.getTankList();
    }

    /**
     * Returns an object with the given ID.
     *
     * @param id unique identifier of an object
     * @return Tank
     */
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Tank getTank(@PathParam("id") Integer id) {
        return tankService.getTankById(id);
    }

    /**
     * Endpoint, which when accessed by a browser enables file download.
     *
     * @return Response
     */
    @GET
    @Path("/download")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadTank() {
        File file = new File("/home/lachaka/Desktop/tank.csv");
        try {
            PrintWriter printWriter = new PrintWriter(file);
            StringBuilder sb = new StringBuilder();
            sb.append("id,name,country,manufacturer,type,weight,length,width,height,crew,speed\n");
            for (Tank tank : tankService.getTankList()) {
                sb.append(tank);
                sb.append("\n");
            }
            /*
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(UPLOAD_PATH)));
            String line;
            bufferedReader.readLine();
            while((line = bufferedReader.readLine()) != null) {
                StringTokenizer st =new StringTokenizer(line,",");
                sb.append(st + "\n");

            }
            */
            printWriter.write(sb.toString());
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Response
                .ok(file)
                .header("Content-Disposition", "attachment; filename=" + file.getName())
                .build();
    }

    /**
     * Save endpoint
     *
     * @param tank - JSON object with id and name - the fields of Tank
     * @return the saved object
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Tank saveTank(Tank tank) {
        return tankService.saveTank(tank);
    }

    /**
     * Updates (in this case replaces) Tank object with the given ID with the one in the parameters list.
     *
     * @param id
     * @param tank
     * @return
     */
    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Tank updateTank(@PathParam("id") Integer id, Tank tank) {
        return tankService.updateTank(id, tank);
    }

    /**
     * Deletes Tank with the given ID
     *
     * @param id
     */
    @DELETE
    @Path("/{id}")
    public void deleteTank(@PathParam("id") Integer id) {
        tankService.deleteTank(id);
    }


    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadPdfFile(@FormDataParam("file") InputStream fileInputStream,
                                  @FormDataParam("file") FormDataContentDisposition fileData) throws Exception {
        BufferedReader bufferedReader;
        String row;
        bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        while ((row = bufferedReader.readLine()) != null) {
            if (row.split(",")[0].equals("id"))
                continue;
            String column[] = row.split(",");
            tankService.saveTank(new Tank(Integer.valueOf(column[0]), column[1], column[2],
                    column[3], column[4], Float.valueOf(column[5]), Float.valueOf(column[6]),
                    Float.valueOf(column[7]), Float.valueOf(column[8]),
                    Integer.valueOf(column[9]), Float.valueOf(column[10])));
        }
        return Response.ok("Successfully uploaded !!!").build();
    }

    @POST
    @Path("/multiple")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createMultipleHotels(List<Tank> tanks) {
        for (Tank tank : tanks) {
            tankService.saveTank(tank);
        }
    }
}
