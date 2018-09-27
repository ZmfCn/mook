/*控制块元素的显示或隐藏*/
function showorHide(id) {
    if (document.getElementById(id).style.display == "none") {
        document.getElementById(id).style.display = "block";
    } else {
        document.getElementById(id).style.display = "none";
    }
}

/*确认删除*/
function validateDel() {
    return confirm("雁过尚留声，你确认要删除该条评论吗？");
}