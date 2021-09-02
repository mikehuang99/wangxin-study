
export default function ({route, req, res, store, next}) {
  if (process.server) {
    store.commit('GET_TOKEN_SERVER', req);
  }
  if (process.client) {
    console.log("进入checkuser.js......")
    store.commit('INIT_WEB');

  }

}
