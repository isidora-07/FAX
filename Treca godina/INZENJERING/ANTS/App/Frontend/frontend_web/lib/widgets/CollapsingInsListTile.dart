import 'package:flutter/material.dart';

class CollapsingInsListTile extends StatefulWidget {
  final String title;
  final IconData icon;
  final AnimationController animationController;
  final bool isSelected;
  final Function onTap;

  CollapsingInsListTile(
      {@required this.title,
        @required this.icon,
        @required this.animationController,
        this.isSelected = false,
        this.onTap});

  @override
  _CollapsingInsListTileState createState() => _CollapsingInsListTileState();
}

class _CollapsingInsListTileState extends State<CollapsingInsListTile> {
  Animation<double> widthAnimation, sizedBoxAnimation;

  @override
  void initState() {
    super.initState();
    widthAnimation =
        Tween<double>(begin: 200, end: 70).animate(widget.animationController);
    sizedBoxAnimation =
        Tween<double>(begin: 10, end: 0).animate(widget.animationController);
  }

  @override
  Widget build(BuildContext context) {
    return InkWell(
      onTap: widget.onTap,
      child: Container(
        decoration: BoxDecoration(
          borderRadius: BorderRadius.all(Radius.circular(16.0)),
          color: widget.isSelected
              ? Color(0xFF00BFA6)
              : Colors.transparent,
        ),
        width: widthAnimation.value,
        margin: EdgeInsets.symmetric(horizontal: 8.0),
        padding: EdgeInsets.symmetric(horizontal: 8.0, vertical: 8.0),
        child:  Row(
          children: <Widget>[
            Icon(
              widget.icon,
              color: widget.isSelected ? Colors.white : Colors.black54,
              size: 33.0,
            ),
            SizedBox(width: sizedBoxAnimation.value),
            (widthAnimation.value >= 190)
                ? Text(widget.title,
                style: widget.isSelected
                    ? TextStyle(color: Colors.white, fontSize: 11.0, fontWeight: FontWeight.w500)
                    : TextStyle(color: Colors.black54, fontSize: 11.0, fontWeight: FontWeight.w500))
                : Container()
          ],
        ),
      ),
    );
  }
}