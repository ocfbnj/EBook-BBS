//根据传递过来的参数name获取对应的值
function getParameter(name) {
    const reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    const r = location.search.substr(1).match(reg);
    if (r !== null) return (r[2]);
    return null;
}