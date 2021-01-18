import http from "../http-common";

class CompanyDataService {
    getAll() {
        return http.get("/companies");
    }

    count(){
        return http.get("/kpi/companies");
    }

     get(id) {
         return http.get(`/company/${id}/offers`);
    }

    create(data) {
         return http.post("/company/offer/create", data);
     }
    //
    // update(id, data) {
    //     return http.put(`/tutorials/${id}`, data);
    // }
    //
    // delete(id) {
    //     return http.delete(`/tutorials/${id}`);
    // }
    //
    // deleteAll() {
    //     return http.delete(`/tutorials`);
    // }
    //
    // findByTitle(title) {
    //     return http.get(`/tutorials?title=${title}`);
    // }
}

export default new CompanyDataService();